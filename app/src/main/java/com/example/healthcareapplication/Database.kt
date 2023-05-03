package com.example.healthcareapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Database(context: Context, s: String, nothing: Nothing?, i: Int) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val str1:String="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(str1);

        val str2:String="create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(str2)

        val str3:String="create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)"
        sqLiteDatabase.execSQL(str3)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }
    fun register(username: String, email: String, pass: String) {
        val cv = ContentValues()
        cv.put("username",username)
        cv.put("email",email)
        cv.put("password",pass)
        val db = writableDatabase
        db.insert("users",null,cv)
        db.close()

    }
    fun login(username: String, password: String): Int {
        var result = 0
        val str = arrayOf(username, password)
        val db=readableDatabase
        val c:Cursor=db.rawQuery("select * from users where username=? and password=?",str)
        if(c.moveToFirst()){
            result=1
        }
        return result
    }

    fun addCart(username: String, product: String,price:Float,otype:String){

        val cv = ContentValues()
        cv.put("username",username)
        cv.put("product",product)
        cv.put("price",price)
        cv.put("otype",otype)
        val db = writableDatabase
        db.insert("cart",null,cv)
        db.close()
    }

    fun checkCart(username: String, product: String): Int {
        var result = 0
        val str = arrayOf(username, product)
        val db=readableDatabase
        val c:Cursor=db.rawQuery("select * from cart where username=? and product=?",str)
        if(c.moveToFirst()){
            result=1
        }
        db.close()
        return result
    }

    fun removeCart(username: String, otype: String) {
        val str = arrayOf(username, otype)
        val db=writableDatabase
        db.delete("cart","username=? and otype=?",str)

        db.close()
    }

    fun getCartData(username: String,otype: String):ArrayList<String>{
        var arr = ArrayList<String>()
        var db=readableDatabase
        val str = arrayOf(username, otype)
        val c:Cursor=db.rawQuery("select * from cart where username=? and otype=?",str)
        if(c.moveToFirst()){
            while (c.moveToNext()) {
                val product = c.getString(1)
                val price = c.getString(2)
                arr.add("$product\$$price")
            }
        }
        db.close()
        return arr
    }

    fun addOrder(
        username:String,
        fullname:String,
        address:String,
        contact:String,
        pincode:Int,
        date:String,
        time: String,
        price: Float,
        otype:String){
        var cv:ContentValues= ContentValues()
        cv.put("username",username)
        cv.put("fullname",fullname)
        cv.put("address",address)
        cv.put("contactno",contact)
        cv.put("pincode",pincode)
        cv.put("date",date)
        cv.put("time",time)
        cv.put("amount",price)
        cv.put("otype",otype)
        var db=writableDatabase
        db.insert("orderplace",null,cv)
        db.close()
    }

    fun getOrderData(username:String):ArrayList<String>{
        var arr = ArrayList<String>()
        var db=readableDatabase
        var str = arrayOf(username)
        var c:Cursor=db.rawQuery("select * from orderplace where username=?",str)
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8))
            }while(c.moveToNext())
        }
        db.close()
        return arr
    }

    fun checkAppointmentExists(
        username:String,
        fullname: String,
        address: String?,
        contact: String?,
        date: String,
        time: String):Int{
        var result=0;
        val str = arrayOfNulls<String>(6)
        str[0] = username
        str[1] = fullname
        str[2] = address
        str[3] = contact
        str[4] = date
        str[5] = time
        var db=readableDatabase
        var c:Cursor=db.rawQuery("select * from orderplace where username=? and fullname=? and address=? and contactno=? and date=? and time=? ",str)
        if(c.moveToFirst()){
            result=1;
        }
        db.close()
        return result


    }
    companion object {
        private const val DATABASE_NAME = "your_database_name"
        private const val DATABASE_VERSION = 1
    }
}
