package com.example.prm_cookie_clicker_coffin_dance_edition.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.prm_cookie_clicker_coffin_dance_edition.R
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SharedPreferencesHolder
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.animate
import kotlinx.android.synthetic.main.activity_end.*
import kotlinx.android.synthetic.main.activity_main.*

class EndActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        final_score.text = "You've scored: ${SharedPreferencesHolder.cookies} cookies"

        final_time.text = formatTime()

        SharedPreferencesHolder.clearPreferences()

        onFinish()

    }

    private fun formatTime() : String{
        var totalSeconds = SharedPreferencesHolder.seconds
        val seconds = totalSeconds%60
        totalSeconds/=60
        val minutes = totalSeconds%60
        val hours = totalSeconds/60

        return "You've played for ${hours}h ${minutes}min and ${seconds}sec :3"
    }

    private fun onFinish(){
        go_to_menu_button.setOnClickListener{
            animate(this, go_to_menu_button)
            finishTheGame()
        }
    }

    private fun finishTheGame(){
        super.finish()
        val intent = Intent(this, StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}