package com.hvr.dronedelievery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.company2.*

class company2 : AppCompatActivity() {

    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    private var foodCate : String = ""

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company2)

        auth = Firebase.auth

        back_company1_fromcompany2.setOnClickListener{
            val intent = Intent(this@company2, Login::class.java)
            startActivity(intent)
        }

        finish_company2.setOnClickListener{
            signUp()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_korea ->
                    if (checked) {
                        foodCate = "한식"
                    }
                R.id.radio_china ->
                    if (checked) {
                        foodCate = "중식"
                    }
                R.id.radio_japan ->
                    if (checked) {
                        foodCate = "일식"
                    }
                R.id.radio_west ->
                    if (checked) {
                        foodCate = "양식"
                    }
            }
        }
    }


    private fun signUp(){
        auth.createUserWithEmailAndPassword(put_email_cp.text.toString(), put_password_cp.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this@company2, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    fetchData()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@company2, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun fetchData() {
        val user = hashMapOf(
            "Email" to put_email_cp.text.toString(),
            "IsShop" to true,
            "Name" to put_name_cp.text.toString(),
            "Password" to put_password_cp.text.toString(),
            "Category" to foodCate
        )

        val userUid = FirebaseAuth.getInstance().currentUser
        if (userUid != null) {
            db.collection("user").document(userUid.uid)
                .set(user)
                .addOnSuccessListener(this) {
                    Toast.makeText(this@company2, "DB 저장 성공", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@company2, Login::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener(this) {
                    Toast.makeText(this@company2, "DB 저장 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }
}