package com.app.androidretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidretrofit.Adapter.UserListAdapter
import com.app.androidretrofit.Interface.ApiService
import com.app.androidretrofit.Model.User
import com.app.androidretrofit.Model.UserListResponse
import com.app.androidretrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var service:ApiService

    lateinit var retrofit: Retrofit
    lateinit var listAdapter: UserListAdapter
    private var userlist= mutableListOf<User>()


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        retrofit=Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create(ApiService::class.java)


        listAdapter= UserListAdapter(this,userlist)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=listAdapter
        loadUserList()

    }

    private fun loadUserList() {
        binding.processBar.visibility=View.VISIBLE
        service.getUserList().enqueue(object :Callback<UserListResponse>{
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                binding.processBar.visibility=View.GONE
                //success
                if (response.isSuccessful){
                    var res =response.body()
                    userlist=res!!.userlist
                    listAdapter.setitem(userlist)
                }


            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                binding.processBar.visibility=View.GONE
                Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
            }

        })


    }
}