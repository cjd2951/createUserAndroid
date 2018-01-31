package com.cdavis.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.RadioGroup
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics


class CreateUserActivity : AppCompatActivity() {

    private val TAG = "CreateUserActivity"
    private var male = true



    private var db: FirebaseFirestore? = null

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)


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
        val firstName = findViewById<View>(R.id.first_name_input) as EditText
        val lastName = findViewById<View>(R.id.last_name_input) as EditText
        val username = findViewById<View>(R.id.username) as EditText
        val first = firstName.text.toString()
        val last = lastName.text.toString()
        val user = username.text.toString()

        val userMap = hashMapOf<String, String>()

        userMap.put("first", first)
        userMap.put("last", last)
        userMap.put("username", user)

        saveUserInDb(userMap)

        var gender = "female"
        if(male){
            gender = "male"
        }
        Toast.makeText(this, "User ${user} (${gender}) created.", Toast.LENGTH_LONG).show()
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


    fun saveUserInDb(user: Map<String, String>){
            // Add a new document with a generated ID
            db!!.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id) }
                    .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }


    }
}
