package com.example.healthcareapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BuyMedicineDetailsActivity : AppCompatActivity() {

    private lateinit var tvPackageName: TextView
    private lateinit var tvTotalCost: TextView
    private lateinit var edDetails: EditText
    private lateinit var btnBack: Button
    private lateinit var btnCart: Button
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_details)

        tvPackageName=findViewById(R.id.textViewBMDPackageName)
        tvTotalCost=findViewById(R.id.textViewBMDTotalCost)
        edDetails=findViewById(R.id.editTextBMDMultiline)
        btnBack=findViewById(R.id.buttonBMDBack)
        btnCart=findViewById(R.id.buttonBMDCart)
        edDetails.setKeyListener(null)
        var intent: Intent =getIntent()
        tvPackageName.setText(intent.getStringExtra("text1"))
        edDetails.setText(intent.getStringExtra("text2"))
        tvTotalCost.setText("Total Cost :"+intent.getStringExtra("text3")+"/-")

        btnBack.setOnClickListener(){
            startActivity(Intent(this@BuyMedicineDetailsActivity,BuyMedicineActivity::class.java))
        }
        btnCart.setOnClickListener(){
            var sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            var username:String= sharedPreferences.getString("username","").toString()
            var product:String=tvPackageName.getText().toString()
            var price: Float = intent.getStringExtra("text3")?.toFloatOrNull() ?: 0f

            var db:Database=Database(applicationContext,"healthcare",null,1)
            if(db.checkCart(username,product)==1){
                Toast.makeText(applicationContext,"Product Already Exist", Toast.LENGTH_SHORT).show()
            }else{
                db.addCart(username,product,price,"medicine")
                Toast.makeText(applicationContext,"Product Inserted To Cart", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@BuyMedicineDetailsActivity,BuyMedicineActivity::class.java))
            }
        }

    }
}