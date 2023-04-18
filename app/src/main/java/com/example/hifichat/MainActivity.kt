package com.example.hifichat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hifichat.ui.theme.HifiChatTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

lateinit var edit:EditText
lateinit var txt: TextView
lateinit var txt5:TextView
lateinit var database: DatabaseReference
lateinit var but:Button
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(R.layout.activity_main)

        var user=sp2.getString("user","").toString()
        var rec=sp2.getString("rec","").toString()

        edit=findViewById(R.id.e1)
        but=findViewById(R.id.b1)
        txt=findViewById(R.id.t2)
        txt5=findViewById(R.id.t3)
txt5.text="user : $user "
        but.setOnClickListener{
            database = FirebaseDatabase.getInstance().getReference("chat")
            if(edit.text.toString()!="") {
                database.child(user).setValue(edit.text.toString())
              edit.setText("")
            }

            database.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val p = snapshot.child(rec).getValue()
                    txt.text = p.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                  })
        }


    }}