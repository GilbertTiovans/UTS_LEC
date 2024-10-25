package com.example.utslec.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.lec_uts.Activity2

import com.example.lec_uts.R

class CreateOrLoginFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_or_login, container, false)


        val createButton: Button = view.findViewById(R.id.create_button)
        createButton.setOnClickListener {
            // Start Activity3 when createButton is clicked
            val intent = Intent(requireContext(), Activity2::class.java)
            startActivity(intent)
        }

        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_to_backOnboard3Fragment)
        }

        return view
    }

}