package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lec_uts.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val progressText = view.findViewById<TextView>(R.id.progressText)
        val kcalText = view.findViewById<TextView>(R.id.kcalText)

        // Set values (this logic comes from the original Activity)
        val progress = 68
        val kcalLeft = 840

        // Set progress
        progressBar.progress = progress

        // Update text views
        progressText.text = "$progress%"
        kcalText.text = "$kcalLeft kcal left"

        return view
    }
}
