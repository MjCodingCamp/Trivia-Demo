package com.mjsiddiqui.trivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

// This is our first fragment, which contains first question of the quiz. And it will be displayed when user starts the quiz.

class FirstFragment : Fragment() {

    private lateinit var userName:String
    //This is first question, which is a default question.
    private val ques:String = "Who is the best cricketer in the world?"
    private var ans:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment and taking reference of some views.
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val button:Button = view.findViewById(R.id.btnNext)
        val radioGroup:RadioGroup = view.findViewById(R.id.ques1)

        //Here I am using Radio Group for the first question so that user can select only one answer. And this answer will store in ans variable.
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.option1) {
                    ans = "Sachin Tendulkar"
                }else if(checkedId == R.id.option2){
                    ans = "Virat Kolli"
                }else if(checkedId == R.id.option3) {
                    ans = "Adam Gilchirst"
                }else{
                    ans = "Jacques Kallis"
                }
            }
        })

        //Here i am adding a listener with the next button.
        button.setOnClickListener {

            //it will replace current fragment with the second fragment, if ans variable is not null. otherwise it will display a toast message.
            if (ans != null) {
                val obj = SecondFragment()

                //Now again i am sending data (user name and answer of ques-1) to the second fragment with the help of user define method setData().
                obj.sendData2(userName, ans!!)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frag_container,obj)?.commit()
            }else{
                Toast.makeText(activity,"Please provide answer",Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    // it is user define method, which i am using to receive data (User Name) from IntroFragment.
    fun sendData(s: String) {
       userName = s
    }

}