package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.company1.*
import kotlinx.android.synthetic.main.user1.*

class company1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company1)

        back_login_fromcompany1.setOnClickListener{
            val intent = Intent(this@company1, Login::class.java)
            startActivity(intent)
        }

        next_company2_fromcompany1.setOnClickListener{
            val intent = Intent(this@company1, company2::class.java)
            startActivity(intent)
        }
    }

}