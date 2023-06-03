package com.example.firebase_read_write

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView =findViewById(R.id.textView)

        // Real Time database Reference
        // https://firekotlin-97b1e-default-rtdb.firebaseio.com/
        database = Firebase.database.reference



        // Writing custom objects to firebase
        val user1 = User("Jack","123")
        database.child("Users").setValue(user1)

        // Reading custom objects from firebase
        val pe = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val u1 = snapshot.getValue<User>()
                textView.text = "Username: ${u1?.userName}" + "\n"+ "Password: ${u1?.password}"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }
        database.child("Users").addValueEventListener(pe)






//        // Write Data to Firebase
//        database.child("price").setValue("1940 $")
//
//
//        // Reading data from firebase
//        val postListener = object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val gold_price = snapshot.value
//
//                textView.text= gold_price.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }
//
//        database.child("price").addValueEventListener(postListener)



    }

}