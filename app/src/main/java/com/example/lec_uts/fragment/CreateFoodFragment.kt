package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.R


class CreateFoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi tombol save_button
        val backButton: ImageView = view.findViewById(R.id.back_button)
        val saveButton: ImageView = view.findViewById(R.id.save_button)

        // Set OnClickListener untuk navigasi ke DetailCreateFoodFragment
        saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_createFoodFragment_to_DetailCreateFragment)
        }
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
