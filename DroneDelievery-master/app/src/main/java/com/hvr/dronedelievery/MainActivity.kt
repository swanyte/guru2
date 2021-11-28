package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

            run() {
                // TODO Auto-generated method stub
                val intent = Intent(this@MainActivity, Login::class.java)
                 startActivity(intent);
                 finish();
            }
        }, 2000);
    }
}