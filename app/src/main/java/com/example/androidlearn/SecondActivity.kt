package com.example.androidlearn

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {


    companion object {
        const val TAG = "wenming"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(MainActivity.TAG, "SecondActivity:onCreate")
    }

    fun back(view: View?) {
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SecondActivity:onStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SecondActivity:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SecondActivity:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(MainActivity.TAG, "SecondActivity:onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SecondActivity:onDestroy")
    }

}