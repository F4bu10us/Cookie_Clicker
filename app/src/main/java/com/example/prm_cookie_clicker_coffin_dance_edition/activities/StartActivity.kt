package com.example.prm_cookie_clicker_coffin_dance_edition.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.prm_cookie_clicker_coffin_dance_edition.R
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SharedPreferencesHolder
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.animate
import kotlinx.android.synthetic.main.activity_start.*
import kotlin.system.exitProcess

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_start)
        SharedPreferencesHolder.sharedPreferences = getSharedPreferences(SharedPreferencesHolder.PREFERENCES_FILENAME, Context.MODE_PRIVATE)
        SharedPreferencesHolder.loadData()
        onStartClick()
        onExitClick()
    }

    private fun onStartClick(){
        startButton.setOnClickListener {
            animate(this,startButton)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onExitClick(){
        exitButton.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }

    }
}