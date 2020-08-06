package com.example.myviewmodel

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText : AppCompatEditText {

    private lateinit var clear: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        clear = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_cancel_24, null) as Drawable

        setOnTouchListener(OnTouchListener { v, event ->
            if(compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClicked = false
                when(layoutDirection) {
                    View.LAYOUT_DIRECTION_RTL -> {
                        clearButtonEnd = (clear.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart = (width - paddingEnd - clear.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClicked = true
                        }
                    }
                }
                when {
                    isClicked -> when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            clear = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_cancel_24, null) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            clear = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_cancel_24, null) as Drawable
                            text?.clear()
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                    }
                    else -> return@OnTouchListener false
                }
            }
            false
        })

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if(s.toString().isNotEmpty()) showClearButton()
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clear, null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Enter your name"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

}