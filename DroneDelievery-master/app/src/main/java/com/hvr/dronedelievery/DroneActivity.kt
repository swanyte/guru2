package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_drone.*
import kotlinx.android.synthetic.main.my_page.*

class DroneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drone)

        back_home.setOnClickListener{
            val intent = Intent(this@DroneActivity, ShopHome::class.java)
            startActivity(intent)
        }

    }
}