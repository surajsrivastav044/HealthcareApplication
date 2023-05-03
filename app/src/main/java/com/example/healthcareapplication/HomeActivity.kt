package com.example.healthcareapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username:String= sharedPreferences.getString("username","").toString()
        // save our data with key and value.
        Toast.makeText(applicationContext, "Welcome "+username, Toast.LENGTH_SHORT).show()

        // for logout code
        val exit:CardView=findViewById(R.id.cardExit)
        exit.setOnClickListener(){
           val editor: SharedPreferences.Editor=sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(applicationContext, "Logout Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@HomeActivity, LoginPageActivity:: class.java))
        }

        // for find doctor code
        val findDoctor:CardView=findViewById(R.id.cardFindDoctor)
        findDoctor.setOnClickListener(){
            startActivity(Intent(this@HomeActivity, FindDoctorActivity:: class.java))
        }

        // for lab test code
        val labTest:CardView=findViewById(R.id.cardLabTest)
        labTest.setOnClickListener(){
            startActivity(Intent(this@HomeActivity, LabTestActivity:: class.java))
        }

        // for order detail code
        var orderDetails:CardView=findViewById(R.id.cardOrderDetail)
        orderDetails.setOnClickListener(){
            startActivity(Intent(this@HomeActivity,OrderDetailsActivity:: class.java))
        }

        // for Buy Medicine code
        var buyMedicine:CardView=findViewById(R.id.cardBuyMedicine)
        buyMedicine.setOnClickListener(){
            startActivity(Intent(this@HomeActivity,BuyMedicineActivity:: class.java))
        }

        // for Health Article code
        var healthArticle:CardView=findViewById(R.id.cardHealthDoctor)
        healthArticle.setOnClickListener(){
            startActivity(Intent(this@HomeActivity,HealthArticleActivity:: class.java))
        }


    }
}