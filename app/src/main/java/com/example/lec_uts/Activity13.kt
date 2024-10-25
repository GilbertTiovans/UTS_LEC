package com.example.lec_uts
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.*

class Activity13 : AppCompatActivity() {

    private lateinit var calendar: Calendar
    private lateinit var txtCurrentMonth: TextView
    private lateinit var calendarGrid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_13)

        // Initialize the Calendar object
        calendar = Calendar.getInstance()

        txtCurrentMonth = findViewById(R.id.txt_current_month)
        calendarGrid = findViewById(R.id.calendar_grid)
        val btnPrevious: ImageButton = findViewById(R.id.btn_previous)
        val btnNext: ImageButton = findViewById(R.id.btn_next)

        // Set the initial month display
        updateCalendarDisplay()

        // Handle "Previous" button click
        btnPrevious.setOnClickListener {
            calendar.add(Calendar.MONTH, -1) // Go to the previous month
            updateCalendarDisplay()
        }

        // Handle "Next" button click
        btnNext.setOnClickListener {
            calendar.add(Calendar.MONTH, 1) // Go to the next month
            updateCalendarDisplay()
        }
    }

    // Function to update the month and year in the TextView and populate the calendar grid
    private fun updateCalendarDisplay() {
        // Set the current month and year in the TextView
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        txtCurrentMonth.text = sdf.format(calendar.time)

        // Populate the calendar grid with days
        populateCalendarGrid()
    }

    // Function to populate the GridLayout with days of the month
    private fun populateCalendarGrid() {
        // Clear the previous views in the GridLayout
        calendarGrid.removeAllViews()

// Add the days of the week (Mon, Tue, etc.)
        val daysOfWeek = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        for (day in daysOfWeek) {
            val dayLabel = TextView(this).apply {
                text = day
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                setPadding(9, 9, 9, 9)
                textSize = 20f
                 
            }
            calendarGrid.addView(dayLabel)
        }

        // Calculate which day of the week the first of the month falls on
        val firstDayOfMonth = calendar.clone() as Calendar
        firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
        val dayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1 // Adjust to start on Monday

        // Get the number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Add empty views to represent days from the previous month
        for (i in 1 until dayOfWeek) {
            val emptyView = TextView(this)
            calendarGrid.addView(emptyView)
        }

        // Add the actual days of the month
        for (day in 1..daysInMonth) {
            val dayView = TextView(this).apply {
                text = day.toString()
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                setPadding(18, 18, 18, 18)
                textSize = 25f
            }
            // Set an OnClickListener if you want to handle day clicks
            dayView.setOnClickListener {
                // Handle day click (e.g., show meals and activities for the selected day)
            }
            calendarGrid.addView(dayView)
        }
    }
}

