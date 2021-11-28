package com.hvr.dronedelievery

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.my_page.*
import kotlinx.android.synthetic.main.my_page_shop.*

class ChangeShopProfile : AppCompatActivity() {
    var imageView: ImageView? = null
    var button: Button? = null

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var shopName = ""
    var shopEmail = ""
    var shopFood = ""

    init{
        fetchData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_page_shop)

        back_shop_my_page.setOnClickListener{
            val intent = Intent(this@ChangeShopProfile, ShopHome::class.java)
            startActivity(intent)
        }

        shop_logout_button.setOnClickListener{
            var t1 = Toast.makeText(this, "로그아웃 되었습니다", Toast.LENGTH_SHORT)
            t1.show()
            val intent = Intent(this@ChangeShopProfile, Login::class.java)
            startActivity(intent)
        }

        shop_delete_button.setOnClickListener{
            var t2 = Toast.makeText(this, "회원탈퇴 되었습니다", Toast.LENGTH_SHORT)
            t2.show()
            val intent = Intent(this@ChangeShopProfile, Login::class.java)
            startActivity(intent)
        }

        imageView = findViewById<View>(R.id.profile) as ImageView
        button = findViewById<View>(R.id.change_profile) as Button
        button!!.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    val `in` =
                        contentResolver.openInputStream(data!!.data!!)
                    val img = BitmapFactory.decodeStream(`in`)
                    `in`!!.close()
                    // 이미지 표시
                    imageView!!.setImageBitmap(img)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun fetchData() {
        val user = FirebaseAuth.getInstance().currentUser

        if(user != null){
            val docRef = db.collection("user").document(user.uid)

            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null){
                        shopName = document.getString("Name") as String
                        shopEmail = document.getString("Email") as String
                        shopFood = document.getString("Category") as String

                        shop_name.text = shopName
                        shop_email.text = shopEmail
                        shop_food.text = shopFood
                    }
                }
        }
    }
}