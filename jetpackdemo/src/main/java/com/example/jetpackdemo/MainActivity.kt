package com.example.jetpackdemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
    }


    fun test() {
        val viewModelProvider = ViewModelProvider(this)
        val studentViewModel = viewModelProvider.get(StudentViewModel::class.java)
        println(studentViewModel.age)
    }

}