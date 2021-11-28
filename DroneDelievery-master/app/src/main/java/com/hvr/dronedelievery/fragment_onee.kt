package com.hvr.dronedelievery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_one.*


class fragment_onee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_one)

        val phoneBook=createFakePhoneBook(faskeNumber = 30)
        val FragmentOneAdapter=FragmentOneAdapter(phoneBook, LayoutInflater.from(this@fragment_onee))
        fragment_one_item.adapter=FragmentOneAdapter
        fragment_one_item.layoutManager=LinearLayoutManager(this@fragment_onee)

    }

    fun createFakePhoneBook(faskeNumber: Int = 10, phoneBook: PhoneBook2 = PhoneBook2()): PhoneBook2 {
        for (i in 1 until faskeNumber) {
            phoneBook.addPerson(
                Person2(count=""+i+"번째 가게의 메뉴갯수",price=""+i+"번째 가게의 가격",name = "" + i + "번째 가게음식", number = "" + i + "번째 가게위치")
            )
        }
        return phoneBook
    }


}

class FragmentOneAdapter(
    val fragmentoneList : PhoneBook2,
    val inflater: LayoutInflater
):RecyclerView.Adapter<FragmentOneAdapter.ViewHolder>(){

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val menuName:TextView
        val gps:TextView
        val price:TextView
        val count:TextView
        init{
            menuName=itemView.findViewById(R.id.textView42)
            gps=itemView.findViewById(R.id.textView44)
            price=itemView.findViewById(R.id.textView36)
            count=itemView.findViewById(R.id.textView30)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=inflater.inflate(R.layout.shop_order_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fragmentoneList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.setText(fragmentoneList.personList.get(position).price)
        holder.menuName.setText(fragmentoneList.personList.get(position).name)
        holder.gps.setText(fragmentoneList.personList.get(position).number)
        holder.count.setText(fragmentoneList.personList.get(position).count)
    }
}
