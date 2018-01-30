package com.cdavis.testapp

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.RadioGroup
import com.cdavis.testapp.R.id.male




class CreateUserActivity : AppCompatActivity() {

    private var male = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val radioGroup = findViewById<RadioGroup>(R.id.gender)
        val maleButton = findViewById<Button>(R.id.male)
        val femaleButton = findViewById<Button>(R.id.female)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.male ->
                    male = true
                R.id.female ->
                    male = false
            }
            setButtonAlphas(maleButton, femaleButton)
        }
    }

    fun onClick(view: View) {
        val input = findViewById<View>(R.id.username) as EditText
        val string = input.text.toString()
        var gender = "female"
        if(male){
            gender = "male"
        }
        Toast.makeText(this, "User ${string} (${gender}) created.", Toast.LENGTH_LONG).show()
    }

    fun setButtonAlphas(maleButton: Button, femaleButton: Button){
        if(male){
            femaleButton.alpha = 0.5f
            maleButton.alpha = 1f
        }else{
            femaleButton.alpha = 1f
            maleButton.alpha = 0.5f
        }
    }
}
