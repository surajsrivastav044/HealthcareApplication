package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView


class DoctorDetailActivity : AppCompatActivity() {
    private val doctorDetails1 = arrayOf(
        arrayOf("Doctor name : Anuj Gupta", "Hospital Address : Barabanki", "Experience : 5years", "Mobile No : 8601246751", "Fees : 600"),
        arrayOf("Doctor name : Gyan Chandra", "Hospital Address : Lucknow", "Experience : 3years", "Mobile No : 639859685", "Fees : 800"),
        arrayOf("Doctor name : Shubham kolar", "Hospital Address : Maharashtra", "Experience : 2years", "Mobile No : 9685968596", "Fees : 600"),
        arrayOf("Doctor name : Raj Shrivastava", "Hospital Address : Bahraich", "Experience : 7years", "Mobile No : 458965896", "Fees : 400"),
        arrayOf("Doctor name : Nitish rana", "Hospital Address : Gorakhpur", "Experience : 8years", "Mobile No : 9123658954", "Fees : 1200")
    )
    private val doctorDetails2 = arrayOf(
        arrayOf("Doctor name : Prajwal", "Hospital Address : Barabanki", "Experience : 5years", "Mobile No : 8601246751", "Fees : 600"),
        arrayOf("Doctor name : Aman bhaskr", "Hospital Address : Lucknow", "Experience : 3years", "Mobile No : 639859685", "Fees : 800"),
        arrayOf("Doctor name : Shubhman Gill", "Hospital Address : Maharashtra", "Experience : 2years", "Mobile No : 9685968596", "Fees : 600"),
        arrayOf("Doctor name : Shiva Shrivastava", "Hospital Address : Bahraich", "Experience : 7years", "Mobile No : 458965896", "Fees : 400"),
        arrayOf("Doctor name : Nitish Kumar", "Hospital Address : Gorakhpur", "Experience : 8years", "Mobile No : 9123658954", "Fees : 1200")
    )
    private val doctorDetails3 = arrayOf(
        arrayOf("Doctor name : Anuj Gupta", "Hospital Address : Barabanki", "Experience : 5years", "Mobile No : 8601246751", "Fees : 600"),
        arrayOf("Doctor name : Gyan Chandra", "Hospital Address : Lucknow", "Experience : 3years", "Mobile No : 639859685", "Fees : 800"),
        arrayOf("Doctor name : Shubham kolar", "Hospital Address : Maharashtra", "Experience : 2years", "Mobile No : 9685968596", "Fees : 600"),
        arrayOf("Doctor name : Raj Shrivastava", "Hospital Address : Bahraich", "Experience : 7years", "Mobile No : 458965896", "Fees : 400"),
        arrayOf("Doctor name : Nitish rana", "Hospital Address : Gorakhpur", "Experience : 8years", "Mobile No : 9123658954", "Fees : 1200")
    )
    private val doctorDetails4 = arrayOf(
        arrayOf("Doctor name : Anuj Gupta", "Hospital Address : Barabanki", "Experience : 5years", "Mobile No : 8601246751", "Fees : 600"),
        arrayOf("Doctor name : Gyan Chandra", "Hospital Address : Lucknow", "Experience : 3years", "Mobile No : 639859685", "Fees : 800"),
        arrayOf("Doctor name : Shubham kolar", "Hospital Address : Maharashtra", "Experience : 2years", "Mobile No : 9685968596", "Fees : 600"),
        arrayOf("Doctor name : Raj Shrivastava", "Hospital Address : Bahraich", "Experience : 7years", "Mobile No : 458965896", "Fees : 400"),
        arrayOf("Doctor name : Nitish rana", "Hospital Address : Gorakhpur", "Experience : 8years", "Mobile No : 9123658954", "Fees : 1200")
    )
    private val doctorDetails5 = arrayOf(
        arrayOf("Doctor name : Anuj Gupta", "Hospital Address : Barabanki", "Experience : 5years", "Mobile No : 8601246751", "Fees : 600"),
        arrayOf("Doctor name : Gyan Chandra", "Hospital Address : Lucknow", "Experience : 3years", "Mobile No : 639859685", "Fees : 800"),
        arrayOf("Doctor name : Shubham kolar", "Hospital Address : Maharashtra", "Experience : 2years", "Mobile No : 9685968596", "Fees : 600"),
        arrayOf("Doctor name : Raj Shrivastava", "Hospital Address : Bahraich", "Experience : 7years", "Mobile No : 458965896", "Fees : 400"),
        arrayOf("Doctor name : Nitish rana", "Hospital Address : Gorakhpur", "Experience : 8years", "Mobile No : 9123658954", "Fees : 1200")
    )

    private lateinit var tv: TextView
    private lateinit var btn: Button
    private var doctorDetails = Array(0) { arrayOf<String>() }
    private var list = ArrayList<String>()
    private var item: HashMap<String, String> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_doctor_detail)
        tv=findViewById(R.id.textViewDDTitle)
        btn=findViewById(R.id.buttonDDBack)
        val it: Intent =intent
        val title: String? =it.getStringExtra("title")
        tv.setText(title)

        if(title.equals("Family Physicians")){
            doctorDetails=doctorDetails1
        }else if(title.equals("Dietician")){
            doctorDetails=doctorDetails2
        }else if(title.equals("Dentist")){
            doctorDetails=doctorDetails3
        }else if(title.equals("Surgeon")){
            doctorDetails=doctorDetails4
        }else{
            doctorDetails=doctorDetails5
        }

        btn.setOnClickListener(){
             startActivity(Intent(this@DoctorDetailActivity,BookAppointmentActivity::class.java))
        }

        val list = ArrayList<HashMap<String, String>>()
        for (i in 0 until doctorDetails.size) {
            item=HashMap<String,String>()
            item.put("Doctor Name", doctorDetails[i][0])
            item.put("Hospital Address", doctorDetails[i][1])
            item.put("Experience", doctorDetails[i][2])
            item.put("Mobile No", doctorDetails[i][3])
            item.put("Fees", doctorDetails[i][4]+"/-")
            list.add(item)
        }

        var sa:SimpleAdapter= SimpleAdapter(this, list, R.layout.multi_lines,
            arrayOf("Doctor Name", "Hospital Address", "Experience", "Mobile No", "Fees"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))


        var lst:ListView=findViewById(R.id.listViewDD)
        lst.setAdapter(sa)

        lst.setOnItemClickListener { adapterView, view, i, l ->
            var it:Intent=Intent(this@DoctorDetailActivity,BookAppointmentActivity::class.java)
            it.putExtra("text1",title)
            it.putExtra("text2",doctorDetails[i][0])
            it.putExtra("text3",doctorDetails[i][1])
            it.putExtra("text4",doctorDetails[i][3])
            it.putExtra("text5",doctorDetails[i][4])
            startActivity(it)

        }
    }
}


