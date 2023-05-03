package com.example.healthcareapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var ed1:EditText
        lateinit var ed2:EditText
        lateinit var ed3:EditText
        lateinit var ed4:EditText
        lateinit var tv:TextView
        lateinit var datePickerDialog: DatePickerDialog
        lateinit var timePickerDialog: TimePickerDialog
        lateinit var dateButton: Button
        lateinit var timeButton: Button
        lateinit var btnBook: Button
        lateinit var btnBack: Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        ed1=findViewById(R.id.app_fullname)
        ed2=findViewById(R.id.app_address)
        ed3=findViewById(R.id.app_contact)
        ed4=findViewById(R.id.app_fees)
        tv=findViewById(R.id.app_title)
        dateButton=findViewById(R.id.buttonAppDate)
        timeButton=findViewById(R.id.buttonAppTime)
        btnBook=findViewById(R.id.bookAppointment)
        btnBack=findViewById(R.id.bookAppointment_back)

        ed1.setKeyListener(null)
        ed2.setKeyListener(null)
        ed3.setKeyListener(null)
        ed4.setKeyListener(null)
        var it: Intent= getIntent()
        var title: String? =it.getStringExtra("text1")
        var fullname: String? =it.getStringExtra("text2")
        var address: String? =it.getStringExtra("text3")
        var contact: String? =it.getStringExtra("text4")
        var fees: String? =it.getStringExtra("text5")

        tv.setText(title)
        ed1.setText(fullname)
        ed2.setText(address)
        ed3.setText(contact)
        ed4.setText(fees+"/-")



        fun initDatePicker() {
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var newMonth = month + 1
                dateButton.setText("$day/$newMonth/$year")

            }
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val style = AlertDialog.THEME_HOLO_DARK
            datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
            datePickerDialog.datePicker.minDate = cal.timeInMillis + 86400000
        }

        fun initTimePicker() {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                timeButton.setText("$hour:$minute")
            }
            val cal = Calendar.getInstance()
            val hrs = cal.get(Calendar.HOUR)
            val mins = cal.get(Calendar.MINUTE)

            val style = AlertDialog.THEME_HOLO_DARK
            timePickerDialog = TimePickerDialog(this, style, timeSetListener, hrs, mins, true)
        }
        // For date Picker
        initDatePicker()
        dateButton.setOnClickListener(){
            datePickerDialog.show()
        }

        // For time Picker
        initTimePicker()
        timeButton.setOnClickListener(){
            timePickerDialog.show()
        }

        // code for book apointment button
        btnBook.setOnClickListener(){
            var sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            var username: String = sharedPreferences.getString("username", "").toString()
            var db: Database = Database(applicationContext, "healthcare", null, 1)
            if(db.checkAppointmentExists(username,title+"=>"+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                Toast.makeText(applicationContext,"Appointment already exist",Toast.LENGTH_SHORT).show()
            }
            else{
                db.addOrder(
                    username,
                    title+"=>"+fullname.toString(),
                    address.toString(),
                    contact.toString(),
                    0,
                    dateButton.getText().toString(),
                    timeButton.getText().toString(),
                    fees?.get(1)?.let { it.code.toFloat() } ?: 0.0f,
                    "appointment"
                )
                startActivity(Intent(this@BookAppointmentActivity, HomeActivity::class.java))
                Toast.makeText(applicationContext, "Appointment Booking success", Toast.LENGTH_SHORT).show()

            }
        }

        // code for book apointment back button
        btnBack.setOnClickListener(){
            startActivity(Intent(this@BookAppointmentActivity,FindDoctorActivity::class.java))
        }


    }
}