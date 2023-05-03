package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class BuyMedicineActivity : AppCompatActivity() {

    private val packages = arrayOf(
        arrayOf("Uprise-D3 1000IU Capsule", "", "", "", "80"),
        arrayOf("Combiflam tablet", "", "", "", "40"),
        arrayOf("Pyridium Sp capsule", "", "", "", "120"),
        arrayOf("Dolo 650 Tablet", "", "", "", "80"),
        arrayOf("Septron tablet", "", "", "", "10"),
        arrayOf("Decadran tablet", "", "", "", "5"),
        arrayOf("Flecxon Tablet", "", "", "", "40"),
        arrayOf("Ampoxin 500mg", "", "", "", "70"),
        arrayOf("Strepsils", "", "", "", "130")
    )
    private val package_details = arrayOf(
            "Building and keeping bones & teeth strong\n"+
                    "Reducing Fatigue/stress and muscular\n"+
                    "Booting Imunity and increasing resistance against infection",
        "Combiflam is a combination medication containing two active ingredients: ibuprofen and paracetamol. It is used to relieve pain, inflammation, and fever. Some of the common conditions for which Combiflam is prescribed include:\n"+
                "Headache\n"+
                "Toothache\n"+
                "Menstrual cramps\n"+
                "Menstrual cramps",
        "Here are the conditions for which Pyridium-Sp capsule is commonly used:\n"+
                "Urinary tract infections (UTIs)\n"+
                "Bladder infections\n"+
                "Kidney stones\n"+
                "Prostate gland enlargement",
        "Pain relief: Dolo 650 can be used to relieve mild to moderate pain caused by headaches, toothaches, menstrual cramps, arthritis, or muscle aches.\n"+
                "Fever reduction: It can also be used to reduce fever caused by infections such as the common cold, flu, or other viral or bacterial infections.\n"+
                "Post-vaccination fever: Dolo 650 can be used to reduce fever after vaccination.\n"+
                "Pain and fever in children: It can be used to treat pain and fever in children over 2 months of age.",
        "Septron tablet is an antibiotic medication that contains Sulfamethoxazole and Trimethoprim as active ingredients.\n"+
                "used for bacterial infections",
        "Allergic reactions: It is used to treat allergic reactions like asthma, hay fever, hives, and allergic skin reactions.\n"+
                "Cancer: Decadran is used in combination with other medications to treat different types of cancer, such as leukemia, lymphoma, and multiple myeloma.\n"+
                "Skin disorders: It is used to treat skin conditions like psoriasis, eczema, and other inflammatory skin conditions.",
        "Flexon Tablet is a combination of three active ingredients - Paracetamol, Ibuprofen, and Chlorzoxazone. \n"+
                "It is mainly used for relieving pain \n"+
                "reducing inflammation",
        "Ampoxim 500mg is an antibiotic medication that contains amoxicillin and is used to treat various bacterial infections such as:\n"+
                "Respiratory tract infections\n"+
                "Urinary tract infections\n"+
                "Skin and soft tissue infections",

        "Amylmetacresol: an antiseptic that can help fight infection in the throat\n"+
                "Dichlorobenzyl alcohol: an antiseptic that can help fight infection in the throat\n"+
                "Lidocaine: a local anaesthetic that can help numb the throat and relieve pain"
    )
    private var list = ArrayList<String>()
    private var item: HashMap<String, String> = HashMap()
    lateinit var btnCard: Button
    lateinit var btnBack: Button
    lateinit var lst: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)

        btnCard=findViewById(R.id.buttonBMCart)
        btnBack=findViewById(R.id.buttonBMBack)
        lst=findViewById(R.id.listViewBM)

        btnBack.setOnClickListener(){
            startActivity(Intent(this@BuyMedicineActivity,HomeActivity::class.java))
        }
        btnCard.setOnClickListener(){
            startActivity(Intent(this@BuyMedicineActivity,CartBuyMedicineActivity::class.java))
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

        lst.setAdapter(sa)

        lst.setOnItemClickListener { adapterView, view, i, l ->
            var it:Intent=Intent(this@BuyMedicineActivity,BuyMedicineDetailsActivity::class.java)
            it.putExtra("text1",packages[i][0])
            it.putExtra("text2",package_details[i])
            it.putExtra("text3",packages[i][4])
            startActivity(it)

        }
    }
}