package com.hvr.dronedelievery


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_inventory2.*

class inventory2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory2)

        getFoodInfoAndDraw()

        back.setOnClickListener {
            onBackPressed()
        }
    }

    fun getFoodInfoAndDraw() {
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        val company = intent.getStringExtra("company")

        food_detail_name.setText(name)
        food_detail_price.setText(number)
        company_Name.setText(company)

    }
}