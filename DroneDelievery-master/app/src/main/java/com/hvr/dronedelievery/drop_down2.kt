

package com.hvr.dronedelievery

import androidx.appcompat.app.AppCompatActivity
//
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.activity_drop_down2.*
//
//
class drop_down2 : AppCompatActivity() {}

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_drop_down2)
//
//
//        val phoneBook = createFakePhoneBook(faskeNumber = 30)
//        val DropDown2Adapter = DropDown2Adapter(
//            dd2List = phoneBook,
//            inflater = LayoutInflater.from(this@drop_down2),
//            activity = this
//        )
//
//        drop_down2_item.adapter = DropDown2Adapter
//        drop_down2_item.layoutManager = LinearLayoutManager(this@drop_down2)
//
//
//
//    }
//
//    fun createFakePhoneBook(faskeNumber: Int = 10, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
//        for (i in 1 until faskeNumber) {
//            phoneBook.addPerson(
//                Person(name = "" + i + "번째 가게", number = "" + i + "번째 가게 대표메뉴")
//            )
//        }
//        return phoneBook
//    }
//
//
//}
//
//class DropDown2Adapter(
//    val dd2List: PhoneBook,
//    val inflater: LayoutInflater,
//    val activity: Activity
//) : RecyclerView.Adapter<DropDown2Adapter.ViewHolder>() {
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val companyName: TextView
//        val mainMenu:TextView
//        init {
//            companyName = itemView.findViewById(R.id.textView4)
//            mainMenu = itemView.findViewById(R.id.textView7)
//
//            itemView.setOnClickListener {
//                val intent = Intent(activity, inventory3::class.java)
//                intent.putExtra("name", dd2List.personList.get(adapterPosition).name)
//                intent.putExtra("number", dd2List.personList.get(adapterPosition).number)
//                activity.startActivity(intent)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = inflater.inflate(R.layout.shop_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return dd2List.personList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.companyName.setText(dd2List.personList.get(position).name)
//        holder.mainMenu.setText(dd2List.personList.get(position).number)
//    }
//}