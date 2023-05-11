package com.app.todolist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.todolist.adapter.UserAdapter
import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.ActivityHomePageBinding
import com.app.todolist.model.User


class HomePageActivity : AppCompatActivity() {
    lateinit var database:UserDataBase
    lateinit var sadapter:UserAdapter
    var userlist= mutableListOf<User>()
    val spinnerList = arrayOf("All Lists", "Default", "Personal", "Shopping", "Work", "Finished")
    lateinit var binding: ActivityHomePageBinding
    lateinit var listadapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)





        var actionbar = supportActionBar
        actionbar?.setTitle("")
        binding.toolBar.title = ""

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spiner.adapter = adapter

        binding.spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected
            }
        }
        binding.floatingButton.setOnClickListener {
            startActivity(Intent(this,DailyWorkActivity::class.java))
        }
       // database=Room.databaseBuilder(this,UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()

        database=Room.databaseBuilder(this,UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()

        sadapter= UserAdapter(this,userlist)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=sadapter
      //  userlist.add(User(1,"vdfdf","fdfdf"))

        updatelist()




    }

    private fun updatelist() {
        userlist=database.userdao().getAllItem()
        sadapter.setitem(userlist)
    }

    override fun onRestart() {
        super.onRestart()
        if (database!=null){
            updatelist()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.menu_setting ->{
                true
            }
            R.id.menu_logout->{
                var preference = getSharedPreferences("dixit", MODE_PRIVATE)

                var editor = preference.edit()

                editor.remove("islogin")
                editor.apply()
                startActivity(Intent(this, LogInActivity::class.java))
                finishAffinity()

                true
            }else->super.onOptionsItemSelected(item)

        }
    }
}