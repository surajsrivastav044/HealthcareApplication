package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HealthArticleDetailsActivity : AppCompatActivity() {
    private lateinit var tv1: TextView
    private lateinit var img: ImageView
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_article_details)
        img=findViewById(R.id.imageViewHAD)
        tv1=findViewById(R.id.textViewHADTitle )
        btnBack=findViewById(R.id.buttonHADBack)

        var intent:Intent=getIntent()
        tv1.setText(intent.getStringExtra("text1"))

        var bundle: Bundle? =getIntent().getExtras()
        if(bundle !=null){
            var resId:Int=bundle.getInt("text2")
            img.setImageResource(resId)
            }
        btnBack.setOnClickListener(){
            startActivity(Intent(this@HealthArticleDetailsActivity,HealthArticleActivity::class.java))
        }

    }
}