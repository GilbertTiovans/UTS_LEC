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

class Onboard2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboard2, container, false)

        // Inisialisasi tombol "Next"
        val nextButton: Button = view.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            // Navigasi ke Onboard3Fragment
            findNavController().navigate(R.id.action_next_to_intro3)
        }

        // Inisialisasi tombol "Back"
        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Navigasi ke fragment sebelumnya
            findNavController().popBackStack()
        }

        return view
    }
}
