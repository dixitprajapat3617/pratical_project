package com.app.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.ActivityDailyWorkBinding
import com.app.todolist.model.User

class DailyWorkActivity : AppCompatActivity() {
    lateinit var database:UserDataBase
    lateinit var binding: ActivityDailyWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDailyWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database=Room.databaseBuilder(this,UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()


        binding.floatingButtonSaved.setOnClickListener {

            var tittle=binding.etTittle.text.toString().trim()
            var description=binding.etDaliyWork.text.toString().trim()
            inserUser(tittle,description)

        }



    }

    private fun inserUser(ctittle:String,cdescription:String) {

        var user=User(tittle = ctittle, description = cdescription)

        try {
                database.userdao().inserUser(user)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            onBackPressed()

        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}