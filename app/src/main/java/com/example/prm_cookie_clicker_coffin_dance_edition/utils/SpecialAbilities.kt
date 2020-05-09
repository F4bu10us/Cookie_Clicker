package com.example.prm_cookie_clicker_coffin_dance_edition.utils

enum class AbilitiesState{None, First, Second, Third, Wizard}

object SpecialAbilities {
    var state: AbilitiesState = AbilitiesState.None
    var assetsMultiplier = 1
    var clickerMultiplier = 1
    var forbiddenKnowledge = false

    fun upgradeToFirst(){
        state = AbilitiesState.First
        assetsMultiplier = 2
    }
    fun upgradeToSecond(){
        state = AbilitiesState.Second
        clickerMultiplier = 3
    }
    fun upgradeToThird(){
        state = AbilitiesState.Third
        forbiddenKnowledge = true
    }
    fun upgradeToWizard(){
        state = AbilitiesState.Wizard
        assetsMultiplier = 3
        clickerMultiplier = 4
    }

    fun setToCurrentAbilities(){
        when(state){
            AbilitiesState.None ->{
                assetsMultiplier = 1
                clickerMultiplier = 1
                forbiddenKnowledge = false
            }
            AbilitiesState.First -> upgradeToFirst()
            AbilitiesState.Second -> upgradeToSecond()
            AbilitiesState.Third -> upgradeToThird()
            AbilitiesState.Wizard -> upgradeToWizard()
        }
    }

}