package com.example.prm_cookie_clicker_coffin_dance_edition.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prm_cookie_clicker_coffin_dance_edition.R
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeAdapter
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeItem
import com.example.prm_cookie_clicker_coffin_dance_edition.fragments.AssetsFragment
import com.example.prm_cookie_clicker_coffin_dance_edition.fragments.CustomizationFragment
import com.example.prm_cookie_clicker_coffin_dance_edition.fragments.MagicFragment
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.animate
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_upgrades.*

class UpgradeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.assets_page ->{
                    replaceFragment(AssetsFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.magic_page ->{
                    replaceFragment(MagicFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.customization_page->{
                    replaceFragment(CustomizationFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_upgrades)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(AssetsFragment())
        onBackClick()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun onBackClick() {
        back_button.setOnClickListener {
            animate(this, back_button)
            finish()
        }
    }



}