package com.hvr.dronedelievery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.my_page_shop.*
import kotlinx.android.synthetic.main.plus_popup.*
import kotlinx.android.synthetic.main.user2.*

class PopupActivity : Activity() {
        var txtText: TextView? = null
        var imagebutton: ImageButton? = null

        lateinit var auth: FirebaseAuth
        private val db = FirebaseFirestore.getInstance()

        //Button button;
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                //타이틀바 없애기
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.plus_popup)

                //UI 객체생성
                txtText = findViewById<View>(R.id.txtText) as TextView

                //데이터 가져오기
                val intent = intent
                val data = intent.getStringExtra("data")
                txtText!!.text = data

                menu_plus_bt.setOnClickListener{
                        plusDB()
                        finish()
                }
        }

        //확인 버튼 클릭
        fun mOnClose(v: View?) {
                //데이터 전달하기
                val intent = Intent()
                intent.putExtra("result", "Close Popup")
                setResult(RESULT_OK, intent)

                //액티비티(팝업) 닫기
                finish()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
                //바깥레이어 클릭시 안닫히게
                return if (event.action == MotionEvent.ACTION_OUTSIDE) {
                        false
                } else true
        }

        override fun onBackPressed() {
                //안드로이드 백버튼 막기
                return
        }

        fun plusDB(){

                var i = 1

                val menu = hashMapOf(
                        "menuName" to edit_menu_name.text.toString(),
                        "menuPrice" to edit_menu_price.text.toString()
                )

                val userUid = FirebaseAuth.getInstance().currentUser
                if (userUid != null) {
                        db.collection("user").document(userUid.uid).collection("menu").document("menu" + i)
                                .set(menu)
                                .addOnSuccessListener(this) {

                                        Toast.makeText(this@PopupActivity, "DB 저장 성공", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener(this) {
                                        Toast.makeText(this@PopupActivity, "DB 저장 실패", Toast.LENGTH_SHORT).show()
                                }
                }
        }
}