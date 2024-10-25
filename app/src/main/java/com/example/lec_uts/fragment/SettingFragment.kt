package com.example.utslec.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.lec_uts.MyProfileActivity
import com.example.lec_uts.AboutusActivity
import com.example.lec_uts.R

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Inisialisasi view
        val backIcon: ImageView = view.findViewById(R.id.backIcon)
        val editProfileButton: Button = view.findViewById(R.id.editProfileButton)
        val aboutUsLayout: ViewGroup = view.findViewById(R.id.aboutUsLayout)

        // Set onClickListener untuk tombol kembali
        backIcon.setOnClickListener {
            activity?.onBackPressed() // Kembali ke fragment/aktivitas sebelumnya
        }

        // Set onClickListener untuk tombol edit profil
        editProfileButton.setOnClickListener {
            val intent = Intent(activity, MyProfileActivity::class.java)
            startActivity(intent)
        }

        // Set onClickListener untuk About Us
        aboutUsLayout.setOnClickListener {
            val intent = Intent(activity, AboutusActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
