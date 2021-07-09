package com.mjsiddiqui.trivia


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

//This is Finish Activity which is a summary which display all summary of your quiz.

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val userText:TextView = findViewById(R.id.userText)
        val ans1Text:TextView = findViewById(R.id.ans1Text)
        val ans2Text:TextView = findViewById(R.id.ans2Text)

        //Here i am trying to receive data through getStringExtra() method because i was send the data as a String.
        val obj = intent
        val userName = obj.getStringExtra("User")
        val ans1 = obj.getStringExtra("Ans1")
        val ans2 = obj.getStringExtra("Ans2")

        //Here is am changing data of the text filed just for making a logical summary.
        if (userName!= null && ans1 != null && ans2 != null){
            userText.text = "Hello: $userName"
            ans1Text.text = "Ans: $ans1"
            ans2Text.text = "Ans: $ans2"
        }

        val button:Button = findViewById(R.id.btnSubmit)

        //Now i am adding a listener with the Submit Button, when you click on this button then it will send your quiz data to the database to future use.
        button.setOnClickListener {

            // Here is getting current data and time, which i will use in history.
            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy   HH:mm")
            val currentDateAndTime: String = simpleDateFormat.format(Date())
            val simpleDate = SimpleDateFormat("HH:mm:ss")
            val time = simpleDate.format(Date())

            val mData = mutableMapOf<String,String>("Name" to userName, "Ans1" to ans1, "Ans2" to ans2, "Date" to currentDateAndTime)
            val mReference = FirebaseDatabase.getInstance().reference
            mReference.child(time).setValue(mData).addOnSuccessListener {
                Toast.makeText(applicationContext,"Upload Success",Toast.LENGTH_SHORT).show()
                val obj = Intent(this,HomeActivity::class.java)
                startActivity(obj)
                this.finish()
            }.addOnFailureListener{
                Toast.makeText(applicationContext,"Upload Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}