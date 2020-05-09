package com.example.prm_cookie_clicker_coffin_dance_edition.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.prm_cookie_clicker_coffin_dance_edition.R
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeItem
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.AbilitiesState
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SharedPreferencesHolder
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SpecialAbilities
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.animate
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var cookiesSecondsHandler: Handler
    val MAIN_GOAL = 100000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        cookieCounter.text = "${SharedPreferencesHolder.cookies}"

        onCookieClick()
        onShop()
        onFinish()
        cookiesSecondsHandler = Handler(Looper.getMainLooper())
        cookiesSecondsHandler.post(updateCookiesSeconds)
    }

    private fun onCookieClick() {
        cookieClick.setOnClickListener{

            animate(this, cookieClick)

            val angle = Random.nextInt(-30, 30)

            cookieClick.rotation = angle.toFloat()
            val cursorAmount = SharedPreferencesHolder.assetsUpgrades[0].amount
            if(cursorAmount>0){
                SharedPreferencesHolder.cookies += (1 + cursorAmount) * SpecialAbilities.clickerMultiplier
            }else{
                SharedPreferencesHolder.cookies++
            }
            cookieCounter.text = "${SharedPreferencesHolder.cookies}"

            SharedPreferencesHolder.savePrimitiveData()
        }
    }

    private val updateCookiesSeconds = object : Runnable {
        override fun run() {
            when(SpecialAbilities.state){
                AbilitiesState.First -> cookieClick.setImageResource(R.drawable.cookie_image_first_stage)
                AbilitiesState.Second -> cookieClick.setImageResource(R.drawable.cookie_image_second_stage)
                AbilitiesState.Third -> cookieClick.setImageResource(R.drawable.cookie_image_third_stage)
                AbilitiesState.Wizard -> cookieClick.setImageResource(R.drawable.cookie_image_final_stage)
                AbilitiesState.None -> cookieClick.setImageResource(R.drawable.cookie_image)
            }
            SharedPreferencesHolder.seconds++
            val income = calcIncome()
            if (income > 0) {
                SharedPreferencesHolder.cookies += income*SpecialAbilities.assetsMultiplier
                cookieCounter.text = "${SharedPreferencesHolder.cookies}"
            }
            SharedPreferencesHolder.saveData()
            if(SharedPreferencesHolder.cookies > MAIN_GOAL) {
                finishTheGame()
                return
            }
            cookiesSecondsHandler.postDelayed(this, 1000)
        }
    }

    private fun calcIncome(): Long {
        var sum = 0L
        sum += calcListIncome(SharedPreferencesHolder.assetsUpgrades).toLong()
        sum += calcListIncome(SharedPreferencesHolder.magicUpgrades).toLong()
        sum += calcListIncome(SharedPreferencesHolder.customUpgrades).toLong()
        return sum
    }

    private fun calcListIncome(list: MutableList<UpgradeItem>) : Int = list.filter {
        it.amount>0
    }.map { it.calcTotalIncome() }.sum()

    private fun onShop() {
        shopButton.setOnClickListener{
            animate(this, shopButton)
            val intent = Intent(this, UpgradeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onFinish(){
        end_button.setOnClickListener{
            animate(this, end_button)
            finishTheGame()
        }
    }

    private fun finishTheGame(){
        cookiesSecondsHandler.removeCallbacks(updateCookiesSeconds)
        super.finish()
        val intent = Intent(this, EndActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
