package com.example.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)


        val drawable = resources.getDrawable(
            R.drawable.spatial_audio_head_track_animation,
            null
        ) as AnimationDrawable
        imageView.setImageDrawable(drawable)

        drawable.start()

    }
}