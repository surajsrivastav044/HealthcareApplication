package com.example.healthcareapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LabTestBookActivity : AppCompatActivity() {
    private lateinit var edname: EditText
    private lateinit var edaddress:EditText
    private lateinit var edcontact:EditText
    private lateinit var edpincode:EditText

    private lateinit var buttonBooking:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_book)

        //Mapping
        edname=findViewById(R.id.editTextBMBFullname)
        edaddress=findViewById(R.id.editTextBMBAddress)
        edpincode=findViewById(R.id.editTextLTBPincode)
        edcontact=findViewById(R.id.editTextBMBContact)
        buttonBooking=findViewById(R.id.buttonBMBook)

        var intent: Intent =getIntent()
        val price = intent.getStringExtra("price")?.toString()?.split(":".toRegex())?.toTypedArray()
        var date: String? =intent.getStringExtra("date")
        var time: String? =intent.getStringExtra("time")

        buttonBooking.setOnClickListener(){
            var sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            var username:String= sharedPreferences.getString("username","").toString()
            var db:Database=Database(applicationContext,"healthcare",null,1)
            db.addOrder(
                username,
                edname.getText().toString(),
                edaddress.getText().toString(),
                edcontact.getText().toString(),
                edpincode.getText().toString().toInt(),
                date.toString(),
                time.toString(),
                price?.get(1)?.toFloat() ?: 0.0f,
                "lab"
            )
            startActivity(Intent(this@LabTestBookActivity,HomeActivity::class.java))
            Toast.makeText(applicationContext,"data Booking success",Toast.LENGTH_SHORT).show()
            db.removeCart(username,"lab")
        }

    }
}