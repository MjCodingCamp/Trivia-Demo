package com.mjsiddiqui.trivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val historyItem = view.findViewById<LinearLayout>(R.id.historyItem)

        val mReference = FirebaseDatabase.getInstance().reference
        mReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children)
                {
                    val output = i.value as Map<String, String>
                    val mView = LayoutInflater.from(activity).inflate(R.layout.history_item,null)
                    val userDate:TextView = mView.findViewById(R.id.gameDate)
                    val userName:TextView = mView.findViewById(R.id.gameUser)
                    val userAns1:TextView = mView.findViewById(R.id.gameAns1)
                    val userAns2:TextView = mView.findViewById(R.id.gameAns2)
                    userDate.text = "Game: ${output.getValue("Date").toString()}"
                    userName.text = "Name: ${output.getValue("Name").toString()}"
                    userAns1.text = output.getValue("Ans1").toString()
                    userAns2.text = output.getValue("Ans2").toString()
                    historyItem.addView(mView)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })


        return view
    }

}