package com.hvr.dronedelievery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import kotlinx.android.synthetic.main.activity_phone_book.*
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView


class PhoneBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book)

        val phoneBook=createFakePhoneBook(20)
        createPhoneBookList(phoneBook)

    }

    fun createFakePhoneBook(faskeNumber: Int = 10, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 0 until faskeNumber) {
            phoneBook.addPerson(
                Person(name = "" + i + "번째 음식", number = "" + i + "번째 가격")
//                Person(company = ""+i+"번째 가게",name = "" + i + "번째 음식", number = "" + i + "번째 가격")
            )
        }
        return phoneBook
    }

    fun createPhoneBookList(phoneBook: PhoneBook) {
        val container = findViewById<LinearLayout>(R.id.menucontainer)
        val layoutInflater = LayoutInflater.from(this@PhoneBookActivity)
        for (i in 1 until phoneBook.personList.size) {
            val view = layoutInflater.inflate(R.layout.food_item, null)
            val foodNameView = view.findViewById<TextView>(R.id.foodname)
            val foodPriceView = view.findViewById<TextView>(R.id.foodprice)
//            val companyNameView=view.findViewById<TextView>(R.id.company_Name)
            foodNameView.setText(phoneBook.personList.get(i).name)
            foodPriceView.setText(phoneBook.personList.get(i).number)
//            companyNameView.setText(phoneBook.personList.get(i).company)
            addSetOnClickLinstener(phoneBook.personList.get(i),view)
            container.addView(view)
        }
    }

    fun addSetOnClickLinstener(person:Person,view: View){
        view.setOnClickListener{
            val intent= Intent(this@PhoneBookActivity, inventory2::class.java)
            intent.putExtra("name",person.name)
            intent.putExtra("number",person.number)
//            intent.putExtra("company",person.company)
            startActivity(intent)
        }
    }
}

class PhoneBook() {
    val personList = ArrayList<Person>()
    fun addPerson(person: Person) {
        personList.add(person)
    }
}
class PhoneBook2() {
    val personList = ArrayList<Person2>()
    fun addPerson(person: Person2) {
        personList.add(person)
    }
}

class Person(val name: String?, var number: String?) {

}

class Person2(val count:String,val price:String, val name: String, var number: String) {

}