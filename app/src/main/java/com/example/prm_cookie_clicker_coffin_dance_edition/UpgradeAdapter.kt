package com.example.prm_cookie_clicker_coffin_dance_edition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.AbilitiesState
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SharedPreferencesHolder
import com.example.prm_cookie_clicker_coffin_dance_edition.utils.SpecialAbilities
import kotlinx.android.synthetic.main.upgrades_items.view.*


class UpgradeAdapter(private val upgradeList: MutableList<UpgradeItem>) : RecyclerView.Adapter<UpgradeAdapter.UpgradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeViewHolder {
        val upgradeView = LayoutInflater.from(parent.context).inflate(R.layout.upgrades_items,
        parent, false)

        return UpgradeViewHolder(upgradeView)
    }

    override fun onBindViewHolder(holder: UpgradeViewHolder, position: Int) {
        val currentItem = upgradeList[position]

        holder.imageView.setImageResource(currentItem.imageLocation)
        holder.mainTextView.text = currentItem.mainText
        holder.additionalTextView.text = currentItem.additionalText
        holder.current_amount.text = "Amount: ${currentItem.amount}"
        holder.income.text = "Income: ${currentItem.income}"
        holder.current_price.text = "Price: ${currentItem.price}"

        holder.itemView.setOnClickListener {
            when {
                currentItem.price > SharedPreferencesHolder.cookies -> {
                    Toast.makeText(it.context, "You don't have enough cookies!", Toast.LENGTH_SHORT)
                        .show()
                }
                currentItem.maxAmount <= currentItem.amount -> {
                    Toast.makeText(it.context, "You already have the MAX possible amount of this item", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(it.context, "Processing...", Toast.LENGTH_SHORT)
                        .show()

                    if(currentItem.type === UpgradeItemType.Magic){
                        if(position == 1 && SpecialAbilities.state !== AbilitiesState.First){
                            refuseGem(it)
                            return@setOnClickListener
                        }else if(position == 2 && SpecialAbilities.state !== AbilitiesState.Second){
                            refuseGem(it)
                            return@setOnClickListener
                        }else if(position == 3 && SpecialAbilities.state !== AbilitiesState.Third){
                            refuseGem(it)
                            return@setOnClickListener
                        }else{
                            when(position){
                                0 -> SpecialAbilities.upgradeToFirst()
                                1 -> SpecialAbilities.upgradeToSecond()
                                2 -> SpecialAbilities.upgradeToThird()
                                3 -> SpecialAbilities.upgradeToWizard()
                            }
                        }
                    }

                    SharedPreferencesHolder.cookies -= currentItem.price
                    currentItem.addUpgrade()

                    if(currentItem.maxAmount == currentItem.amount)holder.current_amount.text = "MAX AMOUNT"
                    when(currentItem.type){
                        UpgradeItemType.Assets -> SharedPreferencesHolder.assetsUpgrades = upgradeList
                        UpgradeItemType.Magic -> SharedPreferencesHolder.magicUpgrades = upgradeList
                        UpgradeItemType.Custom -> SharedPreferencesHolder.customUpgrades = upgradeList
                    }
                    SharedPreferencesHolder.saveData()
                    this.notifyDataSetChanged()
                }
            }
        }

    }

    private fun refuseGem(it: View) {
        Toast.makeText(it.context, "You are too weak for this gem!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun getItemCount(): Int = upgradeList.size

    class UpgradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.upgradeIcon
        val mainTextView: TextView = itemView.upgrade_main_text
        val additionalTextView: TextView = itemView.upgrade_additional_text
        val current_amount: TextView = itemView.upgrade_current_amount
        val income: TextView = itemView.upgrade_income
        val current_price: TextView = itemView.upgrade_current_price
    }

}