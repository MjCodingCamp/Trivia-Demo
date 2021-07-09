package com.mjsiddiqui.trivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


// This is our main activity which I am using it to display as splash screen only for 3 sec.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Here i am using Immersive Sticky mode to display UI on a full screen for batter user experience.
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setContentView(R.layout.activity_main)

        val mRunnable = Runnable {
            Thread.sleep(3000)  // here i am using 3 second delay.

            // after 3 second Home Activity will automatically triggered.
            val obj = Intent(this, HomeActivity::class.java)
            startActivity(obj)
            finish()        //it will finish the activity, so that if we press back key then our splash screen will not show again
        }

        // Here i am creating a separate thread and passing a runnable in which i will switch the activity after 3 second.
        val thread = Thread(mRunnable)
        thread.start()

    }



    override fun onResume() {
        super.onResume()
        // Here i am also using immersive Sticky mode to display UI on a full screen, because if user will switch this app and come back, then onResume will be invoked.
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}