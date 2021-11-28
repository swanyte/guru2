package com.hvr.dronedelievery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

import kotlinx.android.synthetic.main.activity_drop_down.drop_down2_item
import kotlinx.android.synthetic.main.activity_drop_down.goheart
import kotlinx.android.synthetic.main.activity_drop_down.gohome
import kotlinx.android.synthetic.main.activity_drop_down.goorderpage
import kotlinx.android.synthetic.main.activity_drop_down.spinner
import kotlinx.android.synthetic.main.activity_drop_down2.*
import kotlinx.android.synthetic.main.activity_lovee.*


class lovee : AppCompatActivity(), OnItemSelectedListener {
    var k = 0
    var i = 0

    var shopName = arrayOfNulls<String>(100)
    var bestMenu = arrayOfNulls<String>(100)
    var docId = ""

    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()



    init{
        fetchData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lovee)



        gohome.setOnClickListener{
            val intent = Intent(this@lovee, UserHome::class.java)
            startActivity(intent)
        }

        goheart.setOnClickListener{
            val intent = Intent(this@lovee, lovee::class.java)
            startActivity(intent)
        }

        goorderpage.setOnClickListener{
            val intent = Intent(this@lovee, Order_listt::class.java)
            startActivity(intent)
        }


        back_lovee.setOnClickListener{
            val intent = Intent(this@lovee, UserHome::class.java)
            startActivity(intent)
        }

        val place = intent.getIntExtra("place", 4)


    }

    fun jiyun(){
        val phoneBook = createFakePhoneBook(faskeNumber = k)

        val lovee2Adapter = lovee2Adapter(
            lovee2List = phoneBook,
            inflater = LayoutInflater.from(this@lovee),
            activity = this
        )

        love_item.adapter = lovee2Adapter
        love_item.layoutManager = LinearLayoutManager(this@lovee)
    }

    fun fetchData() {

        db.collection("user")
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

        for (i in 1 until k+1) {
            phoneBook.addPerson(
                Person(name = shopName[i], number = bestMenu[i])
            )
        }
        return phoneBook
    }
}

class lovee2Adapter(
    val lovee2List: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
) : RecyclerView.Adapter<lovee2Adapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyName: TextView
        val mainMenu: TextView
        val companyImage: ImageView
        init {
            companyName = itemView.findViewById(R.id.textView4)
            mainMenu = itemView.findViewById(R.id.textView7)
            companyImage = itemView.findViewById(R.id.imageView4)

            itemView.setOnClickListener {
                val intent = Intent(activity, inventory3::class.java)
                intent.putExtra("name", lovee2List.personList.get(adapterPosition).name)
                intent.putExtra("number", lovee2List.personList.get(adapterPosition).number)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lovee2List.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val storageReference = Firebase.storage.reference
        //val storageImg  = Firebase.storage.getReferenceFromUrl("gs://drone-delivery-c012e.appspot.com/l_2019112901003607500286631.jpg")

        holder.companyName.setText(lovee2List.personList.get(position).name)
        holder.mainMenu.setText(lovee2List.personList.get(position).number)
        //holder.companyImage.setImageURI()
    }
}