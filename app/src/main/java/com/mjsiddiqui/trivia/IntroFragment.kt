package com.mjsiddiqui.trivia

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


// This is our IntroFragment which will be displayed when user click over the Quiz tab.

class IntroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        val editText:EditText = view.findViewById(R.id.nameText)
        val button: Button = view.findViewById(R.id.btnStart)

        //Here i am attaching a listener with the button (Start Button), So it will load First Fragment if EditText is not empty otherwise it display some kind of Toast.
        button.setOnClickListener {
            if (editText.text.isNotEmpty())
            {
                // If EditText field is not empty then it will replace current fragment with the help of FirstFragment.
                val obj = FirstFragment()
                obj.sendData(editText.text.toString()) // Here i am sending data from this fragment to First Fragment with the help of user define function (method) which is setData().
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frag_container,obj)?.commit()
            }
            else {
                Toast.makeText(activity, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }


}