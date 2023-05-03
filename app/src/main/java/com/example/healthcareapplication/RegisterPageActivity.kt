package com.example.healthcareapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var username: EditText
        lateinit var email: EditText
        lateinit var password: EditText
        lateinit var confirm_password: EditText
        lateinit var btn: Button
        lateinit var tv: TextView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        username=findViewById(R.id.reg_username)
        email=findViewById(R.id.reg_email)
        password=findViewById(R.id.reg_password)
        confirm_password=findViewById(R.id.reg_confirm_password)
        btn=findViewById(R.id.reg_button)
        tv=findViewById(R.id.already_regitered)
        btn.setOnClickListener(){
            val uname: String = username.text.toString()
            val upass: String = password.text.toString()
            val uemail: String = email.text.toString()
            val ucpass: String = confirm_password.text.toString()
            val db = Database(applicationContext,"healthcare",null,1)
            if(uname.length==0 || upass.length==0 || uemail.length==0 || ucpass.length==0){
                Toast.makeText(applicationContext,"Please Fill All Detail", Toast.LENGTH_SHORT).show()
            }
            else if(upass.equals(ucpass)){
                db.register(uname,uemail,upass)
                Toast.makeText(applicationContext, "Record Inserted Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterPageActivity, LoginPageActivity::class.java))
                }
            else{
                Toast.makeText(applicationContext, "password and Confirm password are wrong", Toast.LENGTH_SHORT).show()
            }
        }
        tv.setOnClickListener(){
            startActivity(Intent(this@RegisterPageActivity, LoginPageActivity::class.java))
        }

    }
}