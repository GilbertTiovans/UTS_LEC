package com.example.lec_uts.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lec_uts.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var calendar: Calendar
    private lateinit var txtCurrentMonth: TextView
    private lateinit var calendarGrid: GridLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calender, container, false)

        // Initialize views
        calendar = Calendar.getInstance()
        txtCurrentMonth = view.findViewById(R.id.txt_current_month)
        calendarGrid = view.findViewById(R.id.calendar_grid)
        val btnPrevious: ImageButton = view.findViewById(R.id.btn_previous)
        val btnNext: ImageButton = view.findViewById(R.id.btn_next)

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

        return view
    }

    // Function to update the month and year in the TextView and populate the calendar grid
    private fun updateCalendarDisplay() {
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        txtCurrentMonth.text = sdf.format(calendar.time)
        populateCalendarGrid()
    }

    // Function to populate the GridLayout with days of the month
    private fun populateCalendarGrid() {
        calendarGrid.removeAllViews()

        // Add the days of the week (Mon, Tue, etc.)
        val daysOfWeek = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        for (day in daysOfWeek) {
            val dayLabel = TextView(context).apply {
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
        val dayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1

        // Get the number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Add empty views for previous month days
        for (i in 1 until dayOfWeek) {
            calendarGrid.addView(TextView(context))
        }

        // Add the actual days of the month
        for (day in 1..daysInMonth) {
            val dayView = TextView(context).apply {
                text = day.toString()
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                setPadding(18, 18, 18, 18)
                textSize = 25f
            }
            dayView.setOnClickListener {
                // Handle day click
            }
            calendarGrid.addView(dayView)
        }
    }
}
