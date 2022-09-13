package com.example.animation

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.graphics.drawable.AnimationDrawable
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var imageView: ImageView
    private lateinit var showImage: Button
    private lateinit var showAnimation: Button

    private lateinit var play1: Button
    private lateinit var play2: Button
    private lateinit var play3: Button

    private var mediaPlayer: MediaPlayer? = null
    private var audioManager: AudioManager? = null


    companion object {
        const val TAG = "SpatialAudioModelImpl"
        const val FILE_NAME_OFF = "spatial_audio_off"
        const val FILE_NAME_FIX = "spatial_audio_fix"
        const val FILE_NAME_HEAD_TRACKING = "spatial_audio_head_tracking"
        const val MUSIC_FORMAT = ".wav"
        const val KEY = "key_spatial_audio"
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        showImage = findViewById(R.id.btn_show_img)
        showAnimation = findViewById(R.id.btn_show_animation)

        play1 = findViewById(R.id.play1)
        play2 = findViewById(R.id.play2)
        play3 = findViewById(R.id.play3)

        val drawable = resources.getDrawable(
            R.drawable.spatial_audio_head_track_animation,
            null
        ) as AnimationDrawable

        showImage.setOnClickListener {
            imageView.setImageResource(R.drawable.ic_launcher_background)
            getAudioFile(this, "spatial_audio_game.m4a")
        }
        showAnimation.setOnClickListener {

            imageView.setImageDrawable(drawable)
            drawable.start()
        }

        play1.setOnClickListener { startPlay(FILE_NAME_OFF, false) }
        play2.setOnClickListener { startPlay(FILE_NAME_FIX, true) }
        play3.setOnClickListener { startPlay(FILE_NAME_HEAD_TRACKING, true) }

    }

    fun getDefaultMusicUri(context: Context, searchFileName: String): Uri? {
        var cursor: Cursor? = null
        var uri: Uri? = null
        val baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        try {
            val projection =
                arrayOf(
                    MediaStore.MediaColumns._ID,
                    MediaStore.MediaColumns.TITLE
                )
            val selection = ("(" + MediaStore.MediaColumns.TITLE + " =?) OR ("
                    + MediaStore.MediaColumns.DISPLAY_NAME + " =?)")
            cursor = context.contentResolver.query(
                baseUri,
                projection,
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                uri = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    cursor.getInt(0).toString()
                )
            }
        } catch (e: Exception) {

        } finally {
            cursor?.close()
        }
        Log.e("wenming", "找到的uri是：$uri")
        return uri
    }

    fun startPlay(fileName: String, spatialAudioEnable: Boolean) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }
        val attributes: AudioAttributes = if (spatialAudioEnable) {
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setIsContentSpatialized(true)
                .setSpatializationBehavior(AudioAttributes.SPATIALIZATION_BEHAVIOR_AUTO)
                .build()
        } else {
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setIsContentSpatialized(false)
                .build()
        }

        try {
            val musicUri = getDefaultMusicUri(this, fileName)

            val fileDescriptor = assets.openFd("$fileName.m4a")
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(fileDescriptor)
            mediaPlayer?.setAudioAttributes(attributes)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("wenming", "播放：" + e.toString())
        }
    }

    fun stopPlay() {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        } catch (e: Exception) {

        }
    }


    override fun onResume() {
        super.onResume()
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        println("是否有音乐播放:" + audioManager.isMusicActive)
    }


    /**
     * 读取共享目录下图片文件
     * @param context  上下文
     * @param filename 文件名称（带后缀a.jpg），是MediaStore查找文件的条件之一
     * @return
     */
    private fun getAudioFile(context: Context, filename: String): Uri? {
        val selection = MediaStore.Audio.Media.DISPLAY_NAME + "='" + filename + "'" //查询条件 “显示名称为？”
        val projection = arrayOf(MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA)
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        ) ?: return null
        for (i in 0 until cursor.count) {
            cursor.moveToNext()
            println(
                "当前音乐：" +
                        cursor.getString(
                            Math.max(
                                0,
                                cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                            )
                        )
            )//音频文件路径+文件名
            //媒体数据库中查询到的文件id
            val columnIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            //通过mediaId获取它的uri
            val mediaId = cursor.getInt(columnIndex)
            //String tPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)); //获取图片路径
            val itemUri =
                Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "" + mediaId)
        }
        cursor.close()
        return null
    }
}