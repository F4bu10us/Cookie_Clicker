package com.example.prm_cookie_clicker_coffin_dance_edition.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.prm_cookie_clicker_coffin_dance_edition.R
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeAdapter
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeItem
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SharedPreferencesHolder
import kotlinx.android.synthetic.main.fragment_assets.view.*

class AssetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_assets, container, false)

        val upgradesList = SharedPreferencesHolder.assetsUpgrades

        view.recycler_view_upgrades.adapter =
            UpgradeAdapter(
                upgradesList
            )

        view.recycler_view_upgrades.layoutManager = LinearLayoutManager(context)
        view.recycler_view_upgrades.setHasFixedSize(true)

        return view
    }

}
