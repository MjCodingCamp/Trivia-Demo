package com.mjsiddiqui.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout

//This is our Home Activity, which display after splash screen. It contains two things first one is TabLayout and second one is FrameLayout, which i will use to display fragment.

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tab:TabLayout = findViewById(R.id.tabLayout)    // Here i am taking reference of TabLayout, which i defined in Xml file.

        //Here i am creating first tab which is Quiz.
        val first = tab.newTab()
        first.text = "Quiz"
        tab.addTab(first,0)

        //Here i am creating second tab which is History.
        val second = tab.newTab()
        second.text = "History"
        tab.addTab(second,1)

        //Here i am adding IntroFragment which will automatically add when Home Activity  will be launched.
        val obj = IntroFragment()
        supportFragmentManager.beginTransaction().add(R.id.frag_container,obj).commit()


        //Now here i am adding listener with TabLayout, When user click over the History tab then History Fragment will be loaded and When user click over the Quiz tab then  IntroFragment will be loaded.
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(t: TabLayout.Tab?) {
                when(t?.position)
                {
                    0->{
                        val obj1 = IntroFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.frag_container,obj1).commit()
                    }
                    1->{
                        val obj2 = HistoryFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.frag_container,obj2).commit()
                    }
                }
            }
            override fun onTabUnselected(t: TabLayout.Tab?) {}
            override fun onTabReselected(t: TabLayout.Tab?) {}
        })
    }
}