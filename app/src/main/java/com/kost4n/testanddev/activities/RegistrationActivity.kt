package com.kost4n.testanddev.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.testanddev.R
import com.kost4n.testanddev.api.Common
import com.kost4n.testanddev.api.ResponseRefreshToken
import com.kost4n.testanddev.api.ResponseRegistration
import com.kost4n.testanddev.api.UsersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var inputUsername: EditText
    private lateinit var button: Button
    private lateinit var api: UsersApi
    private lateinit var phone: String
    private lateinit var accessToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        phone = intent.extras?.getString("phone").toString()
        val numberView = findViewById<TextView>(R.id.number)
        numberView.text = numberView.text.toString() + phone

        inputName = findViewById(R.id.input_name)
        inputUsername = findViewById(R.id.input_username)
        button = findViewById(R.id.confirm_button_reg)
        button.setOnClickListener{view -> onClickReg(view)}
        accessToken = intent.extras?.getString("access_token").toString()
        api = Common.retrofit
    }

    private fun onClickReg(view: View) {
        val name = inputName.toString()
        val username = inputUsername.toString()
        val intentProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
        api.register(phone, name, username)
            .enqueue(object : Callback<ResponseRegistration> {
                override fun onResponse(call: Call<ResponseRegistration>, response: Response<ResponseRegistration>) {
                    startActivity(intentProfileActivity)
                }

                override fun onFailure(call: Call<ResponseRegistration>, t: Throwable) {
                    Toast.makeText(applicationContext, "Не удалось зарегистрироваться", Toast.LENGTH_LONG).show()
                }
            })
    }
}