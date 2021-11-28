package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.id_find.*

class id_find : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.id_find)

        val docRef = db.collection("user")
        docRef.whereEqualTo("Name", id_find_name.text.toString()).get()
            .addOnSuccessListener {document ->
                    if(document != null){
                        
                }
            }

        back_login_fromIdF.setOnClickListener{
            val intent = Intent(this@id_find, Login::class.java)
            startActivity(intent)
        }


    }
}