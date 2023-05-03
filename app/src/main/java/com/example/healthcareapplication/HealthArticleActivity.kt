package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class HealthArticleActivity : AppCompatActivity() {

    private val healthDetails1 = arrayOf(
        arrayOf("Walking Daily", "", "", " ", "Click for more details"),
        arrayOf("Home Care of Covit 19", "", "", " ", "Click for more details"),
        arrayOf("Stop Smoking", "", "", " ", "Click for more details"),
        arrayOf("Health Guts", "", "", " ", "Click for more details"),
        arrayOf("Menstrural Cramps", "", "", " ", "Click for more details")
    )
    private val images = arrayOf(
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5
    )
    private var list = ArrayList<String>()
    private var item: HashMap<String, String> = HashMap()
    private lateinit var btnBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_article)
        btnBack=findViewById(R.id.buttonHABack)
        btnBack.setOnClickListener(){
            startActivity(Intent(this@HealthArticleActivity,HomeActivity::class.java))
        }

        val list = ArrayList<HashMap<String, String>>()
        for (i in 0 until healthDetails1.size) {
            item=HashMap<String,String>()
            item.put("line1", healthDetails1[i][0])
            item.put("line2",healthDetails1[i][1])
            item.put("line3", healthDetails1[i][2])
            item.put("line4", healthDetails1[i][3])
            item.put("line5", healthDetails1[i][4])
            list.add(item)
        }

        var sa: SimpleAdapter = SimpleAdapter(this, list, R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))


        var lst: ListView =findViewById(R.id.listViewHA)
        lst.setAdapter(sa)

        lst.setOnItemClickListener { adapterView, view, i, l ->
            var it:Intent=Intent(this@HealthArticleActivity,HealthArticleDetailsActivity::class.java)
            it.putExtra("text1",healthDetails1[i][0])
            it.putExtra("text2",images[i])
            startActivity(it)

        }
    }
}