package com.hvr.dronedelievery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_two.*


class fragment_twoo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_two)

        val phoneBook=createFakePhoneBook(faskeNumber = 30)
        val FragmentTwoAdapter=FragmentTwoAdapter(phoneBook, LayoutInflater.from(this@fragment_twoo))
        fragment_two_item.adapter=FragmentTwoAdapter
        fragment_two_item.layoutManager=LinearLayoutManager(this@fragment_twoo)

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

class FragmentTwoAdapter(
    val fragmenttwoList : PhoneBook2,
    val inflater: LayoutInflater
):RecyclerView.Adapter<FragmentTwoAdapter.ViewHolder>(){

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
        val view=inflater.inflate(R.layout.shop_ordering_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fragmenttwoList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.setText(fragmenttwoList.personList.get(position).price)
        holder.menuName.setText(fragmenttwoList.personList.get(position).name)
        holder.gps.setText(fragmenttwoList.personList.get(position).number)
        holder.count.setText(fragmenttwoList.personList.get(position).count)
    }
}
