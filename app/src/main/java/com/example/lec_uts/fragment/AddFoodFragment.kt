package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.R


class AddFoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil data dari arguments (data dikirim dari FoodFragment)
        val foodTime = arguments?.getString("foodTime") ?: "Food"

        // Setel judul sesuai dengan data yang diterima
        val titleTextView: TextView = view.findViewById(R.id.foodTimeTitle)
        titleTextView.text = foodTime

        // Inisialisasi ImageView
        val addButton1: ImageView = view.findViewById(R.id.add_button)
        val addButton2: ImageView = view.findViewById(R.id.add_button2)
        val addButton3: ImageView = view.findViewById(R.id.add_button3)
        val addButton4: ImageView = view.findViewById(R.id.add_button4)
        val addButton5: ImageView = view.findViewById(R.id.add_button5)
        val addButton6: ImageView = view.findViewById(R.id.add_button6)
        val backButton: ImageView = view.findViewById(R.id.back_button)
        val createFoodButton: Button = view.findViewById(R.id.create_food_button)

        // Set OnClickListener untuk navigasi ke AddDetailFoodFragment dengan mengirimkan data
        addButton1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Breakfast")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        addButton2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Lunch")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        addButton3.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Dinner")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        addButton4.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Snack")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        addButton5.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Pre-Workout")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        addButton6.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Post-Workout")
            }
            findNavController().navigate(R.id.action_addFoodFragment_to_addDetailFoodFragment, bundle)
        }

        // Set OnClickListener untuk createFoodButton
        createFoodButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFoodFragment_to_createFoodFragment)
        }

        // Set OnClickListener untuk backButton
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFoodFragment_to_FoodFragment)
        }
    }
}
