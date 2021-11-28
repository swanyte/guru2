package com.hvr.dronedelievery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.order_list.*
import kotlinx.android.synthetic.main.shop_home.*


class ShopHome : AppCompatActivity() {

    var k = 0
    var i = 0

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var shopName = ""
    var address = ""
    var shopMenu = ""
    var shopPrice = ""

    init{
        who()
        fetchData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_home)

        auth = Firebase.auth

        droneclick.setOnClickListener{
            val intent = Intent(this@ShopHome, DroneActivity::class.java)
            startActivity(intent)
        }

        shop_my_page.setOnClickListener{
            val intent = Intent(this@ShopHome, ChangeShopProfile::class.java)
            startActivity(intent)
        }
    }

    fun jiyun(){
        val phoneBook= createFakePhoneBook(faskeNumber = 2)
        val ShopHomeAdapter=ShopHomeAdapter(shophomeList = phoneBook, inflater = LayoutInflater.from(this@ShopHome), activity = this)
        shop_home_item.adapter=ShopHomeAdapter
        shop_home_item.layoutManager=LinearLayoutManager(this@ShopHome)
    }

    fun createFakePhoneBook(faskeNumber: Int, phoneBook: PhoneBook2 = PhoneBook2()): PhoneBook2 {
        for (i in 1 until faskeNumber) {
            phoneBook.addPerson(
                Person2(count= "1번째 주문", price = shopPrice, name = shopMenu, number = "서울시 노원구 화랑로621") //문제 부분
            )
        }
        return phoneBook
    }
    fun mOnPopupClick(v: View?) {
        //데이터 담아서 팝업(액티비티) 호출
        val intent = Intent(this, PopupActivity::class.java)
        intent.putExtra("data", "Test Popup")
        startActivityForResult(intent, 1)
    }



    fun fetchData() {
        System.out.println("돌아가는중")

        db.collection("user").document("OHeFAQB8UbSgqrpa4qVfqpq16bw1") // 문제 부분
            .get()
            .addOnSuccessListener { documents ->
                address = documents.getString("Address") as String

                System.out.println(address)

                jiyun()
            }
    }

    fun who(){
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val docRef = db.collection("user").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->

                    shopName = document.getString("Name") as String

                    System.out.println(shopName)
                    shopHome_name.setText(shopName)
                }
        }

        if (user != null) {
            db.collection("user").document(user.uid).collection("menu").document("menu1")
                .get()
                .addOnSuccessListener { result ->
                    shopMenu = result.getString("menuName") as String
                    shopPrice = result.getString("menuPrice") as String

                    System.out.println(shopMenu)
                    System.out.println(shopPrice)
                }
        }
    }

}

class ShopHomeAdapter(
    val shophomeList : PhoneBook2,
    val inflater: LayoutInflater,
    val activity: Activity

):RecyclerView.Adapter<ShopHomeAdapter.ViewHolder>(){

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val menuName:TextView
        val gps:TextView
        val price:TextView
        val count:TextView
        init{
            itemView.setOnClickListener {
                Toast.makeText(activity, "주문 완료", Toast.LENGTH_SHORT).show()
            }
            price=itemView.findViewById(R.id.textView42)
            gps=itemView.findViewById(R.id.textView44)
            menuName=itemView.findViewById(R.id.textView36)
            count=itemView.findViewById(R.id.textView30)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=inflater.inflate(R.layout.shop_order_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shophomeList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.setText(shophomeList.personList.get(position).price)
        holder.menuName.setText(shophomeList.personList.get(position).name)
        holder.gps.setText(shophomeList.personList.get(position).number)
        holder.count.setText(shophomeList.personList.get(position).count)
    }
}