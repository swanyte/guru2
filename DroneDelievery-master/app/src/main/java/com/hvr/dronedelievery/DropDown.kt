package com.hvr.dronedelievery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_drop_down.*
import kotlinx.android.synthetic.main.activity_drop_down.back
import kotlinx.android.synthetic.main.activity_drop_down.drop_down2_item
import kotlinx.android.synthetic.main.activity_drop_down.goheart
import kotlinx.android.synthetic.main.activity_drop_down.gohome
import kotlinx.android.synthetic.main.activity_drop_down.goorderpage
import kotlinx.android.synthetic.main.activity_drop_down.spinner
import kotlinx.android.synthetic.main.activity_drop_down2.*
import kotlinx.android.synthetic.main.activity_main_home.*
import kotlinx.android.synthetic.main.my_page.*
import kotlinx.android.synthetic.main.my_page_shop.*


class DropDown : AppCompatActivity(), OnItemSelectedListener {
    var k = 0
    var i = 0

    var shopName = arrayOfNulls<String>(100)
    var bestMenu = arrayOfNulls<String>(100)
    var docId = ""

    var place = 0

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()


    //Create Spinner
    private var mSpinner: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drop_down2)

        gohome.setOnClickListener{
            val intent = Intent(this@DropDown, UserHome::class.java)
            startActivity(intent)
        }

        goheart.setOnClickListener{
            val intent = Intent(this@DropDown, lovee::class.java)
            startActivity(intent)
        }

        goorderpage.setOnClickListener{
            val intent = Intent(this@DropDown, Order_listt::class.java)
            startActivity(intent)
        }


        back_fromDD.setOnClickListener{
            val intent = Intent(this@DropDown, UserHome::class.java)
            startActivity(intent)
        }


        //find spinner's view
        mSpinner = findViewById<View>(R.id.spinner) as Spinner
        //add on item selected listerners to spinner
        mSpinner!!.onItemSelectedListener = this

        place = intent.getIntExtra("place", 4)


        //create adapter
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.labels_array,
            android.R.layout.simple_spinner_item
        )

        spinner.post(Runnable { spinner.setSelection(place) })


        fetchData()


        //how the spinner will look when it drop downs on click
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //setting adapter to spinner
        mSpinner!!.adapter = adapter
    }

    fun jiyun(){
        val phoneBook = createFakePhoneBook(faskeNumber = k)

        val DropDown2Adapter = DropDown2Adapter(
            dd2List = phoneBook,
            inflater = LayoutInflater.from(this@DropDown),
            activity = this
        )

        drop_down2_item.adapter = DropDown2Adapter
        drop_down2_item.layoutManager = LinearLayoutManager(this@DropDown)
    }

    fun fetchData() {
        place = intent.getIntExtra("place", 4)
        System.out.println(place)

        System.out.println("돌아가는중")
        if(place ==0) {
            db.collection("user")
                .whereEqualTo("Category", "한식")
                .whereEqualTo("IsShop", true)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        k++
                        i++

                        shopName[i] = document.getString("Name") as String
                        docId = document.id

                        System.out.println(shopName[i])

                        db.collection("user").document(docId).collection("menu").document("menu1")
                            .get()
                            .addOnSuccessListener { result ->
                                bestMenu[i] = result.getString("menuName") as String

                                System.out.println(bestMenu[i])
                                jiyun()

                            }
                    }
                }
        }
        else if(place ==1){
            db.collection("user")
                .whereEqualTo("IsShop", true)
                .whereEqualTo("Category", "중식")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        k++
                        i++

                        shopName[i] = document.getString("Name") as String
                        docId = document.id

                        System.out.println(shopName[i])

                        db.collection("user").document(docId).collection("menu").document("menu1")
                            .get()
                            .addOnSuccessListener { result ->
                                bestMenu[i] = result.getString("menuName") as String

                                System.out.println(bestMenu[i])
                                jiyun()

                            }
                    }
                }
        }
        else if(place ==2){
            db.collection("user")
                .whereEqualTo("IsShop", true)
                .whereEqualTo("Category", "일식")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        k++
                        i++

                        shopName[i] = document.getString("Name") as String
                        docId = document.id

                        System.out.println(shopName[i])

                        db.collection("user").document(docId).collection("menu").document("menu1")
                            .get()
                            .addOnSuccessListener { result ->
                                bestMenu[i] = result.getString("menuName") as String

                                System.out.println(bestMenu[i])
                                jiyun()
                            }
                    }
                }
        }
        else if(place ==3){
            db.collection("user")
                .whereEqualTo("Category", "양식")
                .whereEqualTo("IsShop", true)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        k++
                        i++

                        shopName[i] = document.getString("Name") as String
                        docId = document.id

                        System.out.println(shopName[i])

                        db.collection("user").document(docId).collection("menu").document("menu1")
                            .get()
                            .addOnSuccessListener { result ->
                                bestMenu[i] = result.getString("menuName") as String

                                System.out.println(bestMenu[i])
                                jiyun()

                            }
                    }
                }
        }
    }


    //Do something when the item is selected
    override fun onItemSelected(
        adapterView: AdapterView<*>,
        view: View,
        i: Int,
        l: Long
    ) {

        //getting label name of the selected spinner
        val message = adapterView.getItemAtPosition(i).toString()

        //showing in Toast selected item name
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //may keep blank
    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    fun createFakePhoneBook(faskeNumber: Int = k, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        System.out.println(k)
        for (i in 1 until k+1) {
            phoneBook.addPerson(
                Person(name = shopName[i], number = bestMenu[i])
            )
        }
        return phoneBook

    }
}

class DropDown2Adapter(
    val dd2List: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
) : RecyclerView.Adapter<DropDown2Adapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyName: TextView
        //val mainMenu: TextView
        init {
            companyName = itemView.findViewById(R.id.textView4)
            //mainMenu = itemView.findViewById(R.id.textView7)

            itemView.setOnClickListener {
                val intent = Intent(activity, inventory3::class.java)
                intent.putExtra("name", dd2List.personList.get(adapterPosition).name)
                intent.putExtra("number", dd2List.personList.get(adapterPosition).number)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dd2List.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.companyName.setText(dd2List.personList.get(position).name)
        //holder.mainMenu.setText(dd2List.personList.get(position).number)
    }
}