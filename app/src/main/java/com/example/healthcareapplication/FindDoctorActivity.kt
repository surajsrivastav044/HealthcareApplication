package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)

        // for exit code
        val exit:CardView=findViewById(R.id.cardFDBack)
        exit.setOnClickListener(){
            startActivity(Intent(this@FindDoctorActivity, HomeActivity:: class.java))
        }

        // for FamilyPhysician code
        val familyPhysician:CardView=findViewById(R.id.cardFDFamilyPhysician)
        familyPhysician.setOnClickListener(){
            val it=Intent(this@FindDoctorActivity, DoctorDetailActivity:: class.java)
            it.putExtra("title","Family Phyicians")
            startActivity(it)
        }

        // for Dietician code
        val dietician:CardView=findViewById(R.id.cardFDDietician)
        dietician.setOnClickListener(){
            val it=Intent(this@FindDoctorActivity, DoctorDetailActivity:: class.java)
            it.putExtra("title","Dietician")
            startActivity(it)
        }

        // for dentist code
        val dentist:CardView=findViewById(R.id.cardFDDentist)
        dentist.setOnClickListener(){
            val it=Intent(this@FindDoctorActivity, DoctorDetailActivity:: class.java)
            it.putExtra("title","Dentist")
            startActivity(it)
        }

        // for Surgeon code
        val surgeon:CardView=findViewById(R.id.cardFDSurgeon)
        surgeon.setOnClickListener(){
            val it=Intent(this@FindDoctorActivity, DoctorDetailActivity:: class.java)
            it.putExtra("title","Surgeon")
            startActivity(it)
        }

        // for Cardiologist code
        val cardiologist:CardView=findViewById(R.id.cardFDCardiologist)
        cardiologist.setOnClickListener(){
            val it=Intent(this@FindDoctorActivity, DoctorDetailActivity:: class.java)
            it.putExtra("title","Cardiologist")
            startActivity(it)
        }
    }
}