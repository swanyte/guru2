package com.hvr.dronedelievery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.order_list.*


class Order_listt : AppCompatActivity() {

    var k = 0
    var i = 0

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var shopName = arrayOfNulls<String>(100)
    var place = 0

    init{
        fetchData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_listt)

//        back.setOnClickListener {
//            onBackPressed()
//        }

        gohome.setOnClickListener{
            val intent = Intent(this@Order_listt, UserHome::class.java)
            startActivity(intent)
        }

        goheart.setOnClickListener{
            val intent = Intent(this@Order_listt, lovee::class.java)
            startActivity(intent)
        }

        goorderpage.setOnClickListener{
            val intent = Intent(this@Order_listt, Order_listt::class.java)
            startActivity(intent)
        }
    }

    fun createFakePhoneBook(faskeNumber: Int = k, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 1 until faskeNumber) {
            phoneBook.addPerson(
                Person(name = "런치 세트" + i, number = shopName[i])
            )
        }
        return phoneBook
    }

    fun fetchData() {
        System.out.println("돌아가는중")

        db.collection("user")
            .whereEqualTo("IsShop", true)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    k++
                    i++

                    shopName[i] = document.getString("Name") as String

                    System.out.println(k)
                }
                jiyun()
                System.out.println(k)
            }
    }

    fun jiyun(){
        val phoneBook=createFakePhoneBook(faskeNumber = k)
        val OrderListAdapter=OrderListAdapter(phoneBook, LayoutInflater.from(this@Order_listt))
        order_list.adapter=OrderListAdapter
        order_list.layoutManager=LinearLayoutManager(this@Order_listt)
    }

}

class OrderListAdapter(
    val orderlistList: PhoneBook,
    val inflater: LayoutInflater
):RecyclerView.Adapter<OrderListAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val menuName:TextView
        val companyName:TextView
        init{
            menuName=itemView.findViewById(R.id.textView12)
            companyName=itemView.findViewById(R.id.textView10)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=inflater.inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderlistList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuName.setText(orderlistList.personList.get(position).name)
        holder.companyName.setText(orderlistList.personList.get(position).number)
    }
}
