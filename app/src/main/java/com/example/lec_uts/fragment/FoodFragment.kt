package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.R


class FoodFragment : Fragment() {

    // Variabel untuk melacak hari saat ini
    private var currentDay = "Today"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plusBreakfast: ImageView = view.findViewById(R.id.plus_breakfast)
        val plusLunch: ImageView = view.findViewById(R.id.plus_lunch)
        val plusDinner: ImageView = view.findViewById(R.id.plus_dinner)
        val plusSnack: ImageView = view.findViewById(R.id.plus_snack)
        val dayInfo: TextView = view.findViewById(R.id.dayInfo)
        val arrowLeft: ImageView = view.findViewById(R.id.arrowleft)
        val arrowRight: ImageView = view.findViewById(R.id.arrowright)

        // Set nilai awal dayInfo
        dayInfo.text = currentDay

        // Fungsi untuk mengubah dayInfo saat arrowleft diklik
        arrowLeft.setOnClickListener {
            when (currentDay) {
                "Today" -> currentDay = "Yesterday"
                "Tomorrow" -> currentDay = "Today"
            }
            dayInfo.text = currentDay
        }

        // Fungsi untuk mengubah dayInfo saat arrowright diklik
        arrowRight.setOnClickListener {
            when (currentDay) {
                "Today" -> currentDay = "Tomorrow"
                "Yesterday" -> currentDay = "Today"
            }
            dayInfo.text = currentDay
        }

        // Navigasi ke AddFoodFragment dengan data yang dikirim
        plusBreakfast.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Breakfast")
            }
            findNavController().navigate(R.id.action_foodFragment_to_addFoodFragment, bundle)
        }

        plusLunch.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Lunch")
            }
            findNavController().navigate(R.id.action_foodFragment_to_addFoodFragment, bundle)
        }

        plusDinner.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Dinner")
            }
            findNavController().navigate(R.id.action_foodFragment_to_addFoodFragment, bundle)
        }

        plusSnack.setOnClickListener {
            val bundle = Bundle().apply {
                putString("foodTime", "Snack")
            }
            findNavController().navigate(R.id.action_foodFragment_to_addFoodFragment, bundle)
        }
    }
}
