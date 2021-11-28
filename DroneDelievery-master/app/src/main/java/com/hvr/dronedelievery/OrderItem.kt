package com.hvr.dronedelievery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.shop_order_item.*

class OrderItem : AppCompatActivity() {
    var txtResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_order_item)

        //txtResult = (TextView)findViewById(R.id.txtResult);

    }



    //버튼
    fun mOnPopupClick(v: View?) {
        //데이터 담아서 팝업(액티비티) 호출
        val intent = Intent(this, OrderPopup::class.java)
        intent.putExtra("data", "Test Popup")
        startActivityForResult(intent, 1)
    } /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                txtResult.setText(result);
            }
        }
    }

*/
}