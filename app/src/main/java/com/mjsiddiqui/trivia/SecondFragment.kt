package com.mjsiddiqui.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

//This is our second fragment, which contains second question.

class SecondFragment : Fragment() {

    //These are some property which i will late initialize.
    private lateinit var ans1:String
    private lateinit var user: String
    private var ans2 = mutableListOf<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment and taking reference of some views.
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val radio1:RadioButton = view.findViewById(R.id.options1)
        val radio2:RadioButton = view.findViewById(R.id.options2)
        val radio3:RadioButton = view.findViewById(R.id.options3)
        val radio4:RadioButton = view.findViewById(R.id.options4)


        val button:Button = view.findViewById(R.id.btnFinish)

        //Here i am attaching a listener with the Finish Button.
        button.setOnClickListener {
            //It will move to the Finish Activity, if ans2 (List) size is not 0 or 1.
            if(ans2.size != 0 && ans2.size != 1)
            {

                // Here i am using intent to load a new activity/
                val intent = Intent(activity,FinishActivity::class.java)

                //Now i am using same intent to send data through putExtra() method which takes data in kay and value form.
                intent.putExtra("User",user)
                intent.putExtra("Ans1",ans1)
                intent.putExtra("Ans2",ans2.toString())
                startActivity(intent)
            }else{
                Toast.makeText(activity,"Please choose more then one option",Toast.LENGTH_SHORT).show()
            }
        }


        //Here i am attaching listener with the radio button. now you can select more than one option.
        radio1.setOnClickListener {
            ans2.add("White")
        }
        radio2.setOnClickListener {
            ans2.add("Yellow")
        }
        radio3.setOnClickListener {
            ans2.add("Green")
        }
        radio4.setOnClickListener {
            ans2.add("Orange")
        }
        return view
    }

    //This is again user define method which i am using to receive data from first fragment.
    fun sendData2(userName: String, ans: String) {
        user = userName
        ans1 = ans
    }

}