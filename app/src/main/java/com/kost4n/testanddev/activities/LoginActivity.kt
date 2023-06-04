package com.kost4n.testanddev.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.kost4n.testanddev.R
import com.kost4n.testanddev.api.Common
import com.kost4n.testanddev.api.UsersApi
import com.kost4n.testanddev.api.ResponseCheckCode
import com.kost4n.testanddev.api.ResponseSendCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var isFirstClick = true
    private lateinit var inputCode: EditText
    private lateinit var button: Button
    private lateinit var inputNumber: EditText
    private lateinit var inputNumberCountry: EditText
    private lateinit var api: UsersApi
    private lateinit var accessToken: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inputCode = findViewById(R.id.input_code)
        button = findViewById(R.id.confirm_button_login)
        inputNumber = findViewById(R.id.input_number)
        inputNumberCountry = findViewById(R.id.input_number_country)

        api = Common.retrofit
        button.setOnClickListener{ v -> onClickLogin(v)}
    }

    private fun onClickLogin(view: View) {
        if (isFirstClick) {
            val phone = inputNumberCountry.toString() + inputNumber.toString()
            api.sendCode(phone)
                .enqueue(object : Callback<ResponseSendCode> {
                    override fun onResponse(call: Call<ResponseSendCode>, response: Response<ResponseSendCode>) {
                        Log.i("LoginActivity", "---------------------------${response.isSuccessful}-----------------------")
                        inputCode.visibility = View.VISIBLE
                        button.text = R.string.login_button_code_text.toString()
                    }

                    override fun onFailure(call: Call<ResponseSendCode>, t: Throwable) {
                            t.printStackTrace()
                    }
                })

            isFirstClick = false

        } else {
            val phone = inputNumberCountry.toString() + inputNumber.toString()
            val code = inputCode.toString()
            val intentRegistrationActivity = Intent(applicationContext, RegistrationActivity::class.java)
                .putExtra("phone", phone)
            val intentProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
            api.checkCode(phone, code)
                .enqueue(object : Callback<ResponseCheckCode> {
                    override fun onResponse(call: Call<ResponseCheckCode>, response: Response<ResponseCheckCode>) {
                        if (response.body()?.is_user_exist == true) {
                            startActivity(intentProfileActivity
                                .putExtra("access_token" ,accessToken))
                        } else {
                            startActivity(intentRegistrationActivity
                                .putExtra("access_token", accessToken))
                        }
                    }

                    override fun onFailure(call: Call<ResponseCheckCode>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
    }
}