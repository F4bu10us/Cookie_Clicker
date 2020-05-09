package com.example.prm_cookie_clicker_coffin_dance_edition.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.prm_cookie_clicker_coffin_dance_edition.R
import kotlinx.android.synthetic.main.activity_upgrades.*

class CustomizationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customization, container, false)
    }

}
