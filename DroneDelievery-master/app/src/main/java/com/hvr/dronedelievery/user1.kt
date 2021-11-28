package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.password_find.*
import kotlinx.android.synthetic.main.user1.*
import kotlinx.android.synthetic.main.user2.*
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks as OnVerificationStateChangedCallbacks

class user1 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user1)

        auth = Firebase.auth

        back_login_fromuser1.setOnClickListener{
            val intent = Intent(this@user1, Login::class.java)
            startActivity(intent)
        }


        next_user2_fromuser1.setOnClickListener{
            val intent = Intent(this@user1, user2::class.java)
            startActivity(intent)
        }
    }
}