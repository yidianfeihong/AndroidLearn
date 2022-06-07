package com.oplus.settings.feature.display.breathinglight.color

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.preference_demo.breath.BreathingLightColor
import com.example.preference_demo.breath.BreathingLightSettingsColor
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: Settings
 * Description: 用来处理底层设置逻辑
 * Version: 1.0
 * Date : 2022-07-04
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
 * ***********************************/
object BreathingLightColorController {
    private const val TAG = "BreathLightColor"
    private const val NAME = "name"
    private const val DEFAULT_COLOR = "default_color"
    private val executorService: ExecutorService by lazy { Executors.newSingleThreadExecutor() }
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }
    var sSettingsColor: BreathingLightSettingsColor? = null

    interface OnLoadStatusCallback {
        fun onSuccess(settingsColor: BreathingLightSettingsColor)
        fun onFailure()
    }

    @JvmStatic
    fun loadData(context: Context, callback: OnLoadStatusCallback?) {
        val localSettingColor = sSettingsColor
        if (localSettingColor == null) {
            executorService.submit {
                val parseRes = parseColors(context)
                if (parseRes != null) {
                    sSettingsColor = parseRes
                    handler.post {
                        callback?.onSuccess(parseRes)
                    }
                } else {
                    callback?.onFailure()
                }
            }
        } else {
            callback?.onSuccess(localSettingColor)
        }
    }

    private fun parseColors(context: Context): BreathingLightSettingsColor? {
        try {
            val parser = initParser(context)
            val settingsColor = BreathingLightSettingsColor()
            var optionalColors: MutableList<BreathingLightColor>? = null
            var type = parser.eventType
            while (type != XmlPullParser.END_DOCUMENT) {
                when (type) {
                    XmlPullParser.START_TAG -> {
                        when (parser.name) {
                            "breath_type" -> {
                                parseDefaultColor(parser, settingsColor)
                            }
                            "optional_color" -> {
                                optionalColors = mutableListOf()
                            }
                            "color" -> {
                                parseOptionalColor(parser, optionalColors)
                            }
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("breath_light" == parser.name) {
                            break
                        }
                    }
                }
                type = parser.next()
            }
            settingsColor.optionColors = optionalColors
            return settingsColor
        } catch (e: Exception) {
            return null
        }
    }

    private fun initParser(context: Context): XmlPullParser {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("test.xml")
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val parser = xmlPullParserFactory.newPullParser()
        parser.setInput(inputStream, "UTF-8")
        return parser
    }

    private fun parseDefaultColor(
        parser: XmlPullParser,
        settingsColor: BreathingLightSettingsColor
    ) {
        parser.getAttributeValue(null, NAME)?.apply {
            when (this) {
                "phone" -> {
                    settingsColor.callDefaultColor =
                        parser.getAttributeValue(null, DEFAULT_COLOR)
                }
                "notification" -> {
                    settingsColor.notificationDefaultColor =
                        parser.getAttributeValue(null, DEFAULT_COLOR)
                }
                "game" -> {
                    settingsColor.gameDefaultColor =
                        parser.getAttributeValue(null, DEFAULT_COLOR)
                }
            }
        }
    }

    private fun parseOptionalColor(
        parser: XmlPullParser,
        optionColors: MutableList<BreathingLightColor>?
    ): BreathingLightColor {
        val breathLightColor = BreathingLightColor()
        for (i in 0 until parser.attributeCount) {
            breathLightColor.lastColors.add(parser.getAttributeValue(i))
        }
        breathLightColor.color = parser.nextText()
        optionColors?.add(breathLightColor)
        return breathLightColor
    }

    fun calPos(color: String?): Int {
        color ?: return -1
        val optionColors = sSettingsColor?.optionColors
        if (optionColors != null) {
            for (i in optionColors.indices) {
                val breathLightColor = optionColors[i]
                if (color == breathLightColor.color) {
                    return i
                }
            }

            for (pos in optionColors.indices) {
                val breathLightColor = optionColors[pos]
                val lastColors = breathLightColor.lastColors
                for (lastColor in lastColors) {
                    if (color == lastColor) {
                        return pos
                    }
                }
            }
        }
        return -1
    }

    fun saveData(
        context: Context?,
        breathingLightColor: BreathingLightColor,
        breathingLightColor1: BreathingLightColor,
        breathingLightColor2: BreathingLightColor
    ) {


    }

}