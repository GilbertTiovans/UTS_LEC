package com.example.lec_uts

import Exercise
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class OtherExerciseActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseList: ArrayList<Exercise>
    private lateinit var filteredExerciseList: ArrayList<Exercise>
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var fabAddExercise: FloatingActionButton
    private lateinit var searchView: SearchView

    // For debouncing search
    private var searchHandler = Handler()
    private var searchRunnable: Runnable? = null

    companion object {
        const val REQUEST_CODE_EDIT_EXERCISE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_exercise)

        // Initialize Firestore database
        db = FirebaseFirestore.getInstance()

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView_exercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        exerciseList = arrayListOf()
        filteredExerciseList = arrayListOf()
        exerciseAdapter = ExerciseAdapter(filteredExerciseList) { exercise ->
            navigateToEditExercisePage(exercise)
        }
        recyclerView.adapter = exerciseAdapter

        // Initialize SearchView
        searchView = findViewById(R.id.searchView)
        setupSearchView()

        // FloatingActionButton for adding new exercises
        fabAddExercise = findViewById(R.id.btn_add_new_exercise)
        fabAddExercise.setOnClickListener {
            navigateToAddExercisePage()
        }

        // Setup Firestore Realtime Listener
        setupRealtimeListener()

        // Setup ItemTouchHelper for swipe actions
        setupItemTouchHelper()
    }

    // Setup Firestore listener to fetch data in real-time
    private fun setupRealtimeListener() {
        db.collection("Exercises")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("FirestoreError", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    exerciseList.clear()
                    for (document in snapshots) {
                        val exercise = document.toObject(Exercise::class.java)
                        exercise.id = document.id // Set document ID from Firestore
                        exerciseList.add(exercise)
                    }
                    filteredExerciseList.clear()
                    filteredExerciseList.addAll(exerciseList)
                    exerciseAdapter.notifyDataSetChanged()
                } else {
                    Log.d("FirestoreError", "No data found")
                }
            }
    }

    // Setup SearchView with debouncing
    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchRunnable?.let { searchHandler.removeCallbacks(it) }

                searchRunnable = Runnable {
                    filterExercises(newText ?: "")
                }
                searchHandler.postDelayed(searchRunnable!!, 300) // 300ms debounce delay
                return true
            }
        })
    }

    // Filter exercises based on search query
    private fun filterExercises(query: String) {
        filteredExerciseList.clear()

        if (query.isEmpty()) {
            filteredExerciseList.addAll(exerciseList)
        } else {
            for (exercise in exerciseList) {
                if (exercise.name.contains(query, ignoreCase = true)) {
                    filteredExerciseList.add(exercise)
                }
            }
        }
        exerciseAdapter.notifyDataSetChanged()
    }

    // Navigate to the AddNewExerciseActivity
    private fun navigateToAddExercisePage() {
        val intent = Intent(this, AddNewExerciseActivity::class.java)
        startActivity(intent)
    }

    // Navigate to the EditExerciseActivity
    private fun navigateToEditExercisePage(exercise: Exercise) {
        if (exercise.id.isNotEmpty()) {
            val intent = Intent(this, EditExerciseActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivityForResult(intent, REQUEST_CODE_EDIT_EXERCISE)
        } else {
            Toast.makeText(this, "Invalid exercise ID", Toast.LENGTH_SHORT).show()
        }
    }

    // Handling the result after editing
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_EDIT_EXERCISE && resultCode == RESULT_OK) {
            setupRealtimeListener() // Reload data after edit
        }
    }

    // Setup ItemTouchHelper for swipe actions (left: delete, right: edit)
    private fun setupItemTouchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val exercise = exerciseList[position]

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        // Exercise to remove
                        val exerciseToRemove = filteredExerciseList[position]

                        // Delete from Firestore
                        db.collection("Exercises").document(exerciseToRemove.id)
                            .delete()
                            .addOnSuccessListener {
                                // Remove from exerciseList
                                exerciseList.removeAll { it.id == exerciseToRemove.id }

                                // Filter list again
                                filterExercises(searchView.query.toString())

                                // Show snackbar with undo option
                                val snackbar = Snackbar.make(recyclerView, "Exercise removed", Snackbar.LENGTH_LONG)
                                snackbar.setAction("Undo") {
                                    exerciseList.add(exerciseToRemove)
                                    filteredExerciseList.add(exerciseToRemove)
                                    exerciseAdapter.notifyDataSetChanged()
                                }
                                snackbar.show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this@OtherExerciseActivity, "Failed to remove exercise: ${e.message}", Toast.LENGTH_SHORT).show()
                                exerciseAdapter.notifyItemChanged(position) // Reset item if deletion fails
                            }
                    }

                    ItemTouchHelper.RIGHT -> {
                        navigateToEditExercisePage(exercise)
                        exerciseAdapter.notifyItemChanged(position)
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    this@OtherExerciseActivity,
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(android.R.color.holo_red_light)
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .addSwipeRightBackgroundColor(android.R.color.holo_blue_light)
                    .addSwipeRightActionIcon(R.drawable.edit)
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
