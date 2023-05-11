package com.app.androidretrofit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.androidretrofit.Model.User
import com.app.androidretrofit.R
import com.app.androidretrofit.databinding.ItemUserLayoutBinding
import com.bumptech.glide.Glide

class UserListAdapter( var context: Context,var userlist:MutableList<User>) :RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    lateinit var binding: ItemUserLayoutBinding

    class MyViewHolder( var binding: ItemUserLayoutBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding= ItemUserLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user =userlist[position]
        holder.binding.tvName.text="${user.firstname} ${user.lastname} "
        holder.binding.tvEmail.text="${user.email} "

        Glide
            .with(context)
            .load(user.avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivImage)





    }

    override fun getItemCount(): Int {
        return userlist.size
    }
    fun setitem(userlist: MutableList<User>){
        this.userlist=userlist
        notifyDataSetChanged()
    }

}