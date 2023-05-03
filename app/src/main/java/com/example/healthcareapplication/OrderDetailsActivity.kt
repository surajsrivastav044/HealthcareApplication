package com.example.healthcareapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class OrderDetailsActivity : AppCompatActivity() {
    private var list = ArrayList<String>()
    private var item: HashMap<String, String> = HashMap()
    private lateinit var lst: ListView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        btnBack=findViewById(R.id.buttonback)
        lst=findViewById(R.id.listViewOrderDetails)

        btnBack.setOnClickListener(){
            startActivity(Intent(this@OrderDetailsActivity,HomeActivity::class.java))
        }
        var db:Database=Database(applicationContext,"healthcare",null,1)
        var sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        var username:String= sharedPreferences.getString("username","").toString()

        var dbData: ArrayList<String> = db.getOrderData(username)


        var orderDetails = Array(dbData.size) { arrayOfNulls<String>(5) }
        for (i in 0 until orderDetails.size) {
            val arrData = dbData.get(i).toString()
            val strData = arrData.split("\\$".toRegex()).toTypedArray()
            orderDetails[i][0] = strData[0]
            orderDetails[i][1] = strData[1]
            if (strData[7].equals("medicine")) {
                orderDetails[i][3] = "Del :" + strData[4]
            } else {
                orderDetails[i][3] = "Del :" + strData[4] + " " + strData[5]
            }
            orderDetails[i][2] = "Rs. " + strData[6]
            orderDetails[i][4] = strData[7]
        }

        var list = ArrayList<HashMap<String, String>>()
        for (i in 0 until orderDetails.size) {
            item=HashMap<String,String>()
            item.put("line1", orderDetails[i][0].toString())
            item.put("line2", orderDetails[i][1].toString())
            item.put("line3", orderDetails[i][2].toString())
            item.put("line4", orderDetails[i][3].toString())
            item.put("line5", orderDetails[i][4].toString())
            list.add(item)
        }
        var sa: SimpleAdapter = SimpleAdapter(this,list, R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))

        lst.setAdapter(sa)

    }
}