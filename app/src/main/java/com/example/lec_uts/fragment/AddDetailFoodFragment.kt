package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.R


class AddDetailFoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_detail_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton: ImageView = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_addDetailFoodFragment_to_FoodFragment)
        }

        val backButton: ImageView = view.findViewById(R.id.back_button)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
