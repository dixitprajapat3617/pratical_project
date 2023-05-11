package com.app.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.app.todolist.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    val PREF_NAME = "dixit"

    val KEY_NAME = "name"
    val KEY_EMAIL = "email"
    val KEY_PASS = "password"
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {


            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()



            if (name.isEmpty()) {
                binding.etName.error = "enter the name"
                return@setOnClickListener
            } else if (!isValidEmail(email)) {
                binding.etEmail.error = "enter valid email address"
                return@setOnClickListener
            } else if (password.isEmpty()) {
                binding.etPassword.error = "enter valid password"
                return@setOnClickListener

            }else{

            }

            createaccount(name,email,password)



        }


    }

    private fun createaccount(fname:String,femail:String,fpassword:String) {

        val preference=getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        var editor =preference.edit()
        editor.putString(KEY_NAME,fname)
        editor.putString(KEY_EMAIL,femail)
        editor.putString(KEY_PASS,fpassword)
        editor.commit()
        Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
        onBackPressed()

    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

    private fun isValidContact(contact: String): Boolean {
        return contact.length == 10
    }





}




