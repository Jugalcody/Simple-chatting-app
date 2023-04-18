package com.example.hifichat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var but4:Button
lateinit var edit3:EditText
lateinit var sp2:SharedPreferences
lateinit var edit4:EditText
lateinit var ref2:DatabaseReference
class first : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
but4=findViewById(R.id.user_but)
        edit3=findViewById(R.id.user)
        edit4=findViewById(R.id.rec)
        but4.setOnClickListener{

            ref2= FirebaseDatabase.getInstance().getReference("chat")
            if(edit3.text.toString()!="" && edit4.text.toString()!="") {
                ref2.child(edit3.text.toString()).setValue("")

                val i= Intent(this@first,MainActivity::class.java)

                sp2=getSharedPreferences("data", Context.MODE_PRIVATE)

                sp2.edit().apply{putString("user",edit3.text.toString())}.apply()
                sp2.edit().apply{putString("rec",edit4.text.toString())}.apply()
                Toast.makeText(this@first,"hello ${edit3.text}",Toast.LENGTH_LONG).show()
                startActivity(i)
            }
        }
    }
}