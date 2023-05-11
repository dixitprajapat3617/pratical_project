package com.app.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.app.todolist.databinding.ActivityLogInBinding
import java.util.regex.Pattern


class LogInActivity : AppCompatActivity() {
    val PREF_NAME = "dixit"
    val KEY_NAME = "name"
    val KEY_EMAIL = "email"
    val KEY_PASSWORD = "password"
    val KEY_lOGIN = "islogin"

    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSignup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }


        binding.btnLogin.setOnClickListener {
            var email = binding.etLogEmail.text.toString().trim()
            var password = binding.etLoginPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.etLogEmail.error = "enter the email"
                return@setOnClickListener
            } else if (isValidPassword(password)) {
                binding.etLoginPassword.error = "enter valid password"
                return@setOnClickListener
            }
            logIN(email,password)


        }
    }

    private fun logIN(email:String,password: String) {
        val preference = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val memail = preference.getString(KEY_EMAIL, "")
        val mpassword = preference.getString(KEY_PASSWORD, "")
        val mname = preference.getString(KEY_NAME, "")
        if (email == memail && password == mpassword) {
            val editor = preference.edit()
            editor.putBoolean(KEY_lOGIN, true)
            editor.commit()
            Toast.makeText(this, "$mname", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePageActivity::class.java))
        } else {
            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show()
        }

    }

    private fun isValidPassword(password: String): Boolean {
        return password.length == 10
    }
}







