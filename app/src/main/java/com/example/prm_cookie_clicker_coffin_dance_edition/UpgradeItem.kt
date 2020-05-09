package com.example.prm_cookie_clicker_coffin_dance_edition

enum class UpgradeItemType{Assets, Magic, Custom}

data class UpgradeItem(
    val imageLocation: Int,
    val mainText: String,
    val additionalText: String,
    val maxAmount: Int,
    var amount: Int,
    var price: Int,
    var income: Int,
    val type: UpgradeItemType
){
    fun addUpgrade(){
        this.amount++
        this.price += price/10
    }

    fun calcTotalIncome(): Int {
        return this.income * this.amount
    }
    companion object UpgradeInitializer{
        fun initAssetUpgrade() = mutableListOf(
            UpgradeItem(
                R.drawable.cursor_icon,
                "Cursor",
                "Somehow it should help xD",
                12,
                0,
                50,
                2,
                UpgradeItemType.Assets
            ),
            UpgradeItem(
                R.drawable.granny_icon,
                "Granny",
                "Bakes you cookies for free :3",
                9,
                0,
                200,
                6,
                UpgradeItemType.Assets
            ),
            UpgradeItem(
                R.drawable.shop_icon,
                "Money shop",
                "We now can sell money for cookies!",
                6,
                0,
                300,
                24,
                UpgradeItemType.Assets
            ),
            UpgradeItem(
                R.drawable.factory_icon,
                "Factory",
                "It does 0 pollution!",
                3,
                0,
                500,
                120,
                UpgradeItemType.Assets
            )
        )
        fun initMagicUpgrade() = mutableListOf<UpgradeItem>(
            UpgradeItem(
                R.drawable.first_magic_stone,
                "Fire Gem",
                "Tales say it increases the bakery speed...",
                1,
                0,
                100,
                50,
                UpgradeItemType.Magic
            ),
            UpgradeItem(
                R.drawable.second_magic_stone,
                "Nature Gem",
                "Tales say it allows you to create even more cookies from the air...",
                1,
                0,
                100,
                50,
                UpgradeItemType.Magic
            ),
            UpgradeItem(
                R.drawable.third_magic_stone,
                "Knowledge Gem",
                "Tales say it reveals secrets...",
                1,
                0,
                100,
                50,
                UpgradeItemType.Magic
            ),
            UpgradeItem(
                R.drawable.fourth_magic_stone,
                "Honorable Gem",
                "Tales say it makes you a wizard?..",
                1,
                0,
                100,
                50,
                UpgradeItemType.Magic
            )
        )
        fun initCommonUpgrade() = mutableListOf<UpgradeItem>()
    }

}