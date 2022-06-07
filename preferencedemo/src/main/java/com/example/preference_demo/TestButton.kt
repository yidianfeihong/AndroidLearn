package com.example.preference_demo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.coui.appcompat.pressfeedback.COUIPressFeedbackHelper

class TestButton @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(
    context!!, attrs, defStyleAttr
) {

    var pressFeedbackHelper: COUIPressFeedbackHelper =
        COUIPressFeedbackHelper(this, COUIPressFeedbackHelper.CARD_PRESS_FEEDBACK)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> pressFeedbackHelper.executeFeedbackAnimator(true)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> pressFeedbackHelper.executeFeedbackAnimator(
                false
            )
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("wenming", "width:$width\theight:$height")
    }

}