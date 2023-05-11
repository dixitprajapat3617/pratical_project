package com.app.todolist.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.DaliyWorkUiDesignBinding
import com.app.todolist.model.User

class UserAdapter (var context: Context,var daliylist:MutableList<User>):RecyclerView.Adapter<UserAdapter.MyViewHolder>(){
    lateinit var binding: DaliyWorkUiDesignBinding
    lateinit var database:UserDataBase


    class MyViewHolder(var bind:DaliyWorkUiDesignBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            binding= DaliyWorkUiDesignBinding.inflate(LayoutInflater.from(context),parent,false)
            return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user=daliylist[position]
        holder.bind.uiTittle.text="${user.tittle}"
        holder.bind.uiDescription.text="${user.description}"
        holder.bind.carView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                setMessage("Are you sure you wnat to delete this item")
                setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                    database = Room.databaseBuilder(context, UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()
                    var userDao=database.userdao()
                    userDao.deleterecord(user)
                    notifyItemRemoved(position)
                    daliylist.removeAt(position)

                })
                setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                })
            }.show()
            return@setOnLongClickListener true

        }

    }

    override fun getItemCount(): Int {
        return daliylist.size
    }
    fun setitem(daliylist: MutableList<User>){
        this.daliylist=daliylist
        notifyDataSetChanged()
    }
}