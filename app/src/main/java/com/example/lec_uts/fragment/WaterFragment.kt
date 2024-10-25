package com.example.utslec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.lec_uts.R

class WaterFragment : Fragment() {

    private lateinit var customButton: Button
    private lateinit var customLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_water, container, false)

        // Bind views
        customButton = view.findViewById(R.id.btnCustom)
        customLayout = view.findViewById(R.id.customLayout)

        // Set initial visibility to GONE
        customLayout.visibility = View.GONE

        // Set onClickListener for Custom Button
        customButton.setOnClickListener {
            // Toggle visibility of customLayout
            if (customLayout.visibility == View.GONE) {
                customLayout.visibility = View.VISIBLE
            } else {
                customLayout.visibility = View.GONE
            }
        }

        return view
    }
}
