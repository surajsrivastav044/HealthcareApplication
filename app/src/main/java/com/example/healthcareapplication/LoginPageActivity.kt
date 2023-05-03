package com.example.healthcareapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var username: EditText
        lateinit var password: EditText
        lateinit var btn:Button
        lateinit var tv:TextView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        username=findViewById(R.id.login_username)
        password=findViewById(R.id.login_password)
        btn=findViewById(R.id.login_button)
        tv=findViewById(R.id.newuser)
        btn.setOnClickListener(){
            val uname: String = username.text.toString()
            val upass: String = password.text.toString()
            val db = Database(applicationContext,"healthcare",null,1)
            if(uname.length==0 || upass.length==0){
                Toast.makeText(applicationContext,"Please Fill All Detail",Toast.LENGTH_SHORT).show()
            }
            else {
                if(db.login(uname,upass)==1){
                    Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                    val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    // save our data with key and value.
                    editor.putString("username", uname)
                    editor.apply()

                    startActivity(Intent(this@LoginPageActivity, HomeActivity:: class.java))
                }
                else {
                    Toast.makeText(applicationContext, "User Not exist please registered first", Toast.LENGTH_SHORT).show()
                }
            }
        }
        tv.setOnClickListener(){
            startActivity(Intent(this@LoginPageActivity, RegisterPageActivity::class.java))
        }
    }
}