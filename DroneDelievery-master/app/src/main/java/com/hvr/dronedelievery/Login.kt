package com.hvr.dronedelievery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login.*


class Login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = Firebase.auth

        change_password_find.setOnClickListener{
            val intent = Intent(this@Login, password_find::class.java)
            startActivity(intent)
        }

        change_user_start.setOnClickListener{
            val intent = Intent(this@Login, user2::class.java)
            startActivity(intent)
        }

        change_shop_start.setOnClickListener{
            val intent = Intent(this@Login, company2::class.java)
            startActivity(intent)
        }

        button_login.setOnClickListener{
            signUp()
        }
    }
    private fun signUp(){

        auth.signInWithEmailAndPassword(username_input.text.toString(), password_input.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this@Login, "로그인 성공", Toast.LENGTH_SHORT).show()
                    who()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun who(){

        val isShop : Boolean = true
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val docRef = db.collection("user").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null){
                        val isShop = document.getBoolean("IsShop")

                        if(isShop!!) {
                            val intent = Intent(this@Login, ShopHome::class.java)
                            startActivity(intent)
                        }
                        else {
                            val intent = Intent(this@Login, UserHome::class.java)
                            startActivity(intent)
                        }
                    }
                }
        }
    }
}