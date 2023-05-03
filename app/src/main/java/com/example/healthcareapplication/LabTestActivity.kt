package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class LabTestActivity : AppCompatActivity() {
    private val packages = arrayOf(
        arrayOf("Package 1 : Full Body Checkup", "", "", "", "800"),
        arrayOf("Package 2 : Blood Glucose Fasting", "", "", "", "400"),
        arrayOf("Package 3 : COVIT-19 Antibody-Ig6", "", "", "", "1200"),
        arrayOf("Package 4 : Thyroid Check", "", "", "", "900"),
        arrayOf("Package 5 : Imunity Check", "", "", "", "600"),
    )
    private val package_details = arrayOf(
               "Complete Blood Count\n"+
               "Blood Glucose Test\n"+
               "Lipid Profile\n"+
               "Liver Function Test\n"+
               "Thyroid Function Test\n"+
                       "COVIT-19 Antibody-Ig6\n"+
                       "Blood Glucose Fasting\n"+
                       "Thyroid Check",
                       "Imunity Check",
        "COVIT-19 Antibody-Ig6",
                "Blood Glucose Fasting",
                "Thyroid Check\n"+
                "Imunity Check\n"+
                       "Imunity Check\n"+
                       "COVIT-19 Antibody-Ig6\n"+
                       "Blood Glucose Fasting\n"+
                       "Thyroid Check\n"+
                       "Imunity Check"

    )
    private var list = ArrayList<String>()
    private var item: HashMap<String, String> = HashMap()
    lateinit var btnCard: Button
    lateinit var btnBack: Button
    lateinit var listView:ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)

        btnCard=findViewById(R.id.buttonLTCart)
        btnBack=findViewById(R.id.buttonLTBack)
        listView=findViewById(R.id.listViewLT)

        btnBack.setOnClickListener(){
            startActivity(Intent(this@LabTestActivity,HomeActivity::class.java))
        }

        val list = ArrayList<HashMap<String, String>>()
        for (i in 0 until packages.size) {
            item=HashMap<String,String>()
            item.put("line1", packages[i][0])
            item.put("line2", packages[i][1])
            item.put("line3", packages[i][2])
            item.put("line4", packages[i][3])
            item.put("line5", "Total Cost "+packages[i][4]+"/-")
            list.add(item)
        }
        var sa: SimpleAdapter = SimpleAdapter(this, list, R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))

        listView.setAdapter(sa)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            var it:Intent=Intent(this@LabTestActivity,LabTestDetailsActivity::class.java)
            it.putExtra("text1",packages[i][0])
            it.putExtra("text2",package_details[i])
            it.putExtra("text3",packages[i][4])
            startActivity(it)

        }

        btnCard.setOnClickListener(){
            startActivity(Intent(this@LabTestActivity,CartLabActivity::class.java))
        }
    }
}


