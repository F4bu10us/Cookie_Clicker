package com.example.prm_cookie_clicker_coffin_dance_edition.utils

import android.content.SharedPreferences
import com.example.prm_cookie_clicker_coffin_dance_edition.UpgradeItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object SharedPreferencesHolder {
    val COOKIES = "Cookies"
    val SECONDS = "Seconds"
    val ASSETS_UPGRADES = "Assets_upgrades"
    val MAGIC_UPGRADES = "Magic_upgrades"
    val CUSTOM_UPGRADES = "Custom_upgrades"
    val ABILITIES = "Abilities"
    val PREFERENCES_FILENAME = "Cookie_Preferences"

    var cookies = 0L
    var seconds = 0L
    var assetsUpgrades : MutableList<UpgradeItem> = UpgradeItem.initAssetUpgrade()
    var magicUpgrades : MutableList<UpgradeItem> = UpgradeItem.initMagicUpgrade()
    var customUpgrades : MutableList<UpgradeItem> = UpgradeItem.initCommonUpgrade()

    lateinit var sharedPreferences: SharedPreferences

    fun saveData() {
        savePrimitiveData().let {
            it.putString(ASSETS_UPGRADES, Gson().toJson(assetsUpgrades))
            it.putString(MAGIC_UPGRADES, Gson().toJson(magicUpgrades))
            it.putString(CUSTOM_UPGRADES, Gson().toJson(customUpgrades))

            it.apply()
        }
    }

    fun savePrimitiveData() : SharedPreferences.Editor{
        sharedPreferences.let {
            val editor = it.edit();
            editor.putLong(COOKIES, cookies)
            editor.putLong(SECONDS, seconds)
            editor.putString(ABILITIES, SpecialAbilities.state.name)
            editor.apply()
            return editor
        }
    }

    fun loadData(){
        sharedPreferences.let {
            cookies = it.getLong(COOKIES, 0L)
            seconds = it.getLong(SECONDS, 0L)
            val enum = it.getString(ABILITIES, "None")
            enum?.let {
                SpecialAbilities.state = AbilitiesState.valueOf(it)
            }
            SpecialAbilities.setToCurrentAbilities()
            val jsonAssetsUpgrades = it.getString(ASSETS_UPGRADES, "")
            val jsonMagicUpgrades = it.getString(MAGIC_UPGRADES, "")
            val jsonCustomUpgrades = it.getString(CUSTOM_UPGRADES, "")

            val gson = Gson();
            if (!jsonAssetsUpgrades.equals("")) {
                val upgradeListType: Type = object : TypeToken<ArrayList<UpgradeItem?>?>() {}.type
                assetsUpgrades = gson.fromJson(jsonAssetsUpgrades, upgradeListType)
                }
            if (!jsonMagicUpgrades.equals("")) {
                val upgradeListType: Type = object : TypeToken<ArrayList<UpgradeItem?>?>() {}.type
                magicUpgrades = gson.fromJson(jsonMagicUpgrades, upgradeListType)
                }
            if (!jsonCustomUpgrades.equals("")) {
                val upgradeListType: Type = object : TypeToken<ArrayList<UpgradeItem?>?>() {}.type
                customUpgrades = gson.fromJson(jsonCustomUpgrades, upgradeListType)
                }
        }
    }

    fun clearPreferences(){
        cookies = 0
        seconds = 0
        assetsUpgrades = UpgradeItem.initAssetUpgrade()
        magicUpgrades = UpgradeItem.initMagicUpgrade()
        customUpgrades = UpgradeItem.initCommonUpgrade()
        SpecialAbilities.state = AbilitiesState.None
        SpecialAbilities.setToCurrentAbilities()
        saveData()
    }
}