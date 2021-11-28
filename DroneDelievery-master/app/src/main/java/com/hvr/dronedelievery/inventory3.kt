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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_drop_down.*
import kotlinx.android.synthetic.main.activity_inventory2.*
import kotlinx.android.synthetic.main.activity_inventory3.*


class inventory3 : AppCompatActivity() {
    var k = 0
    var i = 0

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    var menuName = arrayOfNulls<String>(100)
    var menuPrice = arrayOfNulls<String>(100)

    init{
        fetchData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory3)
    }


    fun createFakePhoneBook(faskeNumber: Int = k, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 1 until k+1) {
            phoneBook.addPerson(
                Person(name = menuName[i], number = menuPrice[i])
            )
        }
        return phoneBook
    }

    fun jiyun(){
        val phoneBook = createFakePhoneBook(faskeNumber = k)
        val foodAdapter = foodAdapter(
            foodList = phoneBook,
            inflater = LayoutInflater.from(this@inventory3),
            activity = this
        )

        inventoryre.adapter = foodAdapter

        inventoryre.layoutManager = LinearLayoutManager(this@inventory3)
    }


    fun fetchData() {
        System.out.println("돌아가는중")

        db.collection("user").document("eqCYua98oYMhrgNqQ4PQpYZw6RI2").collection("menu")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    k++
                    i++

                    menuName[i] = document.getString("menuName") as String
                    menuPrice[i] = document.getString("menuPrice") as String

                    System.out.println(k)
                }
                jiyun()
                System.out.println(k)
            }
    }
}

class foodAdapter(
    val foodList: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
) : RecyclerView.Adapter<foodAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView
        val foodPrice: TextView
//        val companyName:TextView

        init {
            foodName = itemView.findViewById(R.id.foodname)
            foodPrice = itemView.findViewById(R.id.foodprice)
//            companyName = itemView.findViewById(R.id.company_Name)
            itemView.setOnClickListener {
                val intent = Intent(activity, UserHome::class.java)
                Toast.makeText(activity, "주문 완료", Toast.LENGTH_SHORT).show()
                intent.putExtra("name", foodList.personList.get(adapterPosition).name)
                intent.putExtra("number", foodList.personList.get(adapterPosition).number)
//                intent.putExtra("company", foodList.personList.get(adapterPosition).company)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.food_item, parent, false)
//        val view2 = inflater.inflate(R.layout.activity_inventory3, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodName.setText(foodList.personList.get(position).name)
        holder.foodPrice.setText(foodList.personList.get(position).number)
//        holder.companyName.setText(foodList.personList.get(position).company)
    }
}