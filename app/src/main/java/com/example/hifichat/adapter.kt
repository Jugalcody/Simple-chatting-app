package com.example.hifichat

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(var context:Context,var list:ArrayList<MessageList>): RecyclerView.Adapter<adapter.MyViewHolder>() {

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var user=view.findViewById<TextView>(R.id.msg1)
        var rece=view.findViewById<TextView>(R.id.msg2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val i=LayoutInflater.from(parent.context).inflate(R.layout.msg,parent,false)
        return MyViewHolder(i)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val msgs=list[position]
         if(msgs.user!="") {
             holder.user.visibility=View.VISIBLE
             holder.user.text = msgs.user
holder.rece.visibility=View.GONE
         }
         if(msgs.rec!="") {
             holder.rece.visibility=View.VISIBLE
             holder.rece.text = msgs.rec
             holder.user.visibility=View.GONE
         }

    }
}