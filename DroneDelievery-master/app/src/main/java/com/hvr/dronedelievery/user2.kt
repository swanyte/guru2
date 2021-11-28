package com.hvr.dronedelievery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.user2.*

class user2 : AppCompatActivity() {

    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user2)

        auth = Firebase.auth

        back_user1_fromuser2.setOnClickListener{
            val intent = Intent(this@user2, Login::class.java)
            startActivity(intent)
        }

        finish_user2.setOnClickListener{
            signUp()
        }
    }

    private fun signUp(){
        auth.createUserWithEmailAndPassword(put_email.text.toString(), put_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this@user2, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    fetchData()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@user2, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun fetchData() {
        val user = hashMapOf(
            "Email" to put_email.text.toString(),
            "IsShop" to false,
            "Name" to put_name.text.toString(),
            "Password" to put_password.text.toString(),
            "Phone" to put_phone.text.toString(),
            "Address" to put_address.text.toString()
        )

        val userUid = FirebaseAuth.getInstance().currentUser
        if (userUid != null) {
            db.collection("user").document(userUid.uid)
                .set(user)
                .addOnSuccessListener(this) {
                    Toast.makeText(this@user2, "DB 저장 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@user2, Login::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener(this) {
                    Toast.makeText(this@user2, "DB 저장 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }
}