package com.example.healthcareapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CartLabActivity : AppCompatActivity() {

    private var list = ArrayList<HashMap<String, String>>()
    private var item: HashMap<String, String> = HashMap()
    private lateinit var datePickerDialog:DatePickerDialog
    private lateinit var timePickerDialog:TimePickerDialog
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var btnBack: Button
    private lateinit var btnCheckout: Button
    private lateinit var lst:ListView
    private lateinit var tvTotal1:TextView
    var packages = Array(0) { arrayOfNulls<String>(0) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_lab)
        btnDate=findViewById(R.id.buttonCartDatePicker)
        btnTime=findViewById(R.id.buttonCartTimePicker)
        btnBack=findViewById(R.id.buttonCartBack)
        btnCheckout=findViewById(R.id.buttonCartCheckout)
        tvTotal1=findViewById<TextView>(R.id.textViewCartTotalPrice)
        lst=findViewById(R.id.listViewCart)
        var sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        var username:String= sharedPreferences.getString("username","").toString()
        var db:Database=Database(applicationContext,"healthcare",null,1)
        var totalAmount:Float=0f
        var dbData: ArrayList<String> = db.getCartData(username, "lab")

        packages = Array(dbData.size) { arrayOfNulls<String>(5) }

        for (i in 0 until dbData.size) {
            val arrData = dbData[i].toString()
            val strData = arrData.split("\\$".toRegex()).toTypedArray()
            packages[i][0] = strData[0]
            packages[i][4] = "Cost :${strData[1]}/-"
            totalAmount += strData[1].toFloat()
        }

        tvTotal1.setText("Total Cost:"+totalAmount)

        list = ArrayList<HashMap<String, String>>()
        for (i in 0 until packages.size) {
            item=HashMap<String,String>()
            item.put("line1", packages[i][0].toString())
            item.put("line2", packages[i][1].toString())
            item.put("line3", packages[i][2].toString())
            item.put("line4", packages[i][3].toString())
            item.put("line5", packages[i][4].toString())
            list.add(item)
        }
        var sa: SimpleAdapter = SimpleAdapter(this, list, R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        lst.setAdapter(sa)

        btnBack.setOnClickListener(){
            startActivity(Intent(this@CartLabActivity,LabTestActivity::class.java))
        }

        btnCheckout.setOnClickListener(){
            var it:Intent=Intent(this@CartLabActivity,LabTestBookActivity::class.java)
            it.putExtra("price",tvTotal1.getText())
            it.putExtra("date",btnDate.getText())
            it.putExtra("time",btnTime.getText())
            startActivity(it)
        }

        fun initDatePicker() {
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var newMonth = month + 1
                btnDate.setText("$day/$newMonth/$year")

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
                btnTime.setText("$hour:$minute")
            }
            val cal = Calendar.getInstance()
            val hrs = cal.get(Calendar.HOUR)
            val mins = cal.get(Calendar.MINUTE)

            val style = AlertDialog.THEME_HOLO_DARK
            timePickerDialog = TimePickerDialog(this, style, timeSetListener, hrs, mins, true)
        }
        // For date Picker
        initDatePicker()
        btnDate.setOnClickListener(){
            datePickerDialog.show()
        }
        initTimePicker()
        btnTime.setOnClickListener(){
            timePickerDialog.show()
        }


    }
}