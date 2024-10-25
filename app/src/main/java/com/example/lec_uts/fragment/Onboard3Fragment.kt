package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.R


class Onboard3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboard3, container, false)

        // Inisialisasi tombol "Next"
        val nextButton: Button = view.findViewById(R.id.create_button)
        nextButton.setOnClickListener {
            // Navigasi ke Onboard3Fragment
            findNavController().navigate(R.id.action_next_to_CreateOrLoginFragment)
        }

        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}