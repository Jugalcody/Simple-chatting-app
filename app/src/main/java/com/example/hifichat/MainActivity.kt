package com.example.hifichat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.Gravity
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.hifichat.ui.theme.HifiChatTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

lateinit var edit:EditText
lateinit var txt: EditText
val delay=1000
var s=""
lateinit var recycler:RecyclerView
lateinit var adapter2: adapter
var kk=""
var list=ArrayList<MessageList>()
lateinit var handler:Handler
private var messageDisplayed=false
lateinit var txt5:TextView
lateinit var database: DatabaseReference
lateinit var but:Button
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//list.add(MessageList("kkkk",""))
        list.clear()
        //  list.add(MessageList("","boloooo"))
        var user = sp2.getString("user", "").toString()
        var rec = sp2.getString("rec", "").toString()
        var tt = 0
        recycler = findViewById(R.id.recycler)

        edit = findViewById(R.id.e1)
        but = findViewById(R.id.b1)
        //txt=findViewById(R.id.t2)
        txt5 = findViewById(R.id.t3)
        txt5.text = "user : $user "
        but.setOnClickListener {

            tt = 0
            database = FirebaseDatabase.getInstance().getReference("chat")
            if (edit.text.toString() != "") {
                if (tt == 0) {

                    database.child(user).setValue(edit.text.toString())
                    list.add(MessageList(edit.text.toString(), ""))

                    // list.add(MessageList("ooo bhaiii",""))

                    adapter2 = adapter(this@MainActivity, list)
                    println(list)
                    recycler.adapter = adapter2
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)


                    //txt.append("\n\n$user : ${edit.text.toString()}")
                    tt = 1
                    //  database.child(user).setValue("")
                    //txt.gravity= Gravity.START
                }


                edit.setText("")
            }


        }


        recycler = findViewById(R.id.recycler)
        // var rec=sp2.getString("rec","").toString()
        // var user=sp2.getString("user","").toString()

        database = FirebaseDatabase.getInstance().getReference("chat")


       displayMessages()


    }

    fun displayMessages() {
        var rec = sp2.getString("rec", "").toString()
        val msgh = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
        msgh.postDelayed({

            if (!messageDisplayed) {
                database.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val p = snapshot.child(rec).getValue()

                        if (p != "") {
                            //s = "\n\n$rec : ${p.toString()}"
                            // txt.gravity= Gravity.START
                            //txt.append(s)

                            list.add(MessageList("", p.toString()))
                            // list.add(MessageList("ooo bhaiii",""))

                            adapter2 = adapter(this@MainActivity, list)
                            println("jjjj : " + p)
                            recycler.adapter = adapter2
                            recycler.layoutManager = LinearLayoutManager(this@MainActivity)


                            //s = ""
                            database.child(rec).setValue("")

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

                messageDisplayed = true
            }
        }, 1)
    }

    override fun onRestart() {
        list.clear()
        database = FirebaseDatabase.getInstance().getReference("chat")
        database.setValue("")
        super.onRestart()
    }

    override fun onBackPressed() {
        list.clear()
        database = FirebaseDatabase.getInstance().getReference("chat")
        database.removeValue()

        super.onBackPressed()
    }
}