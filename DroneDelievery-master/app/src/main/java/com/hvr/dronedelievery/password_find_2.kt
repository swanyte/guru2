package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_password_find_2.*

class password_find_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_find_2)


        pw_find2_bt.setOnClickListener{
            val intent = Intent(this@password_find_2, Login::class.java)
            startActivity(intent)
        }
    }
}