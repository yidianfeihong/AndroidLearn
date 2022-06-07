package com.example.androidlearn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "wenming"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity:onCreate")
    }

    fun jump(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity:onStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity:onPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity:onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity:onDestroy")
    }


    override fun onNewIntent(p0: Intent?) {
        super.onNewIntent(p0)
        Log.d(TAG, "MainActivity:onNewIntent")
    }


}