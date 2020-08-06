package com.example.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableButton()

        my_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                enableButton()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        my_button.setOnClickListener{Toast.makeText(this@MainActivity, my_edit_text.text, Toast.LENGTH_SHORT).show()}
    }

    private fun enableButton() {
        val res = my_edit_text.text
        my_button.isEnabled = res != null && res.toString().isNotEmpty()
    }
}