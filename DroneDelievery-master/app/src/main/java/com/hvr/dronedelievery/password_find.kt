package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.id_find.*
import kotlinx.android.synthetic.main.password_find.*

class password_find : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_find)

        auth = Firebase.auth

        back_login_fromPwF.setOnClickListener{
            val intent = Intent(this@password_find, Login::class.java)
            startActivity(intent)
        }

        pw_find_bt.setOnClickListener{
            auth.sendPasswordResetEmail(pw_find_id.text.toString())

            val intent = Intent(this@password_find, password_find_2::class.java)
            startActivity(intent)
        }
    }
}