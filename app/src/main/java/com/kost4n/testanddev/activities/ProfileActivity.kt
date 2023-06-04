package com.kost4n.testanddev.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.kost4n.testanddev.R
import com.kost4n.testanddev.api.Common
import com.kost4n.testanddev.api.ResponseGetCurrentUser
import com.kost4n.testanddev.api.UsersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class ProfileActivity: AppCompatActivity() {
    private lateinit var avatar: ImageView
    private lateinit var phone: TextView
    private lateinit var username: TextView
    private lateinit var city: TextView
    private lateinit var birthdayDate: TextView
    private lateinit var zodiac: TextView
    private lateinit var about: TextView
    private lateinit var button: Button
    private lateinit var api: UsersApi
    private lateinit var accessToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        avatar = findViewById(R.id.avatar)
        phone = findViewById(R.id.phoneProfile)
        username = findViewById(R.id.usernameProfile)
        city = findViewById(R.id.city)
        birthdayDate = findViewById(R.id.date_of_birth)
        zodiac = findViewById(R.id.zodiac)
        about = findViewById(R.id.about)
        button = findViewById(R.id.button_profile)
        button.setOnClickListener { view -> onClick(view) }
        accessToken = intent.extras?.getString("access_token").toString()

        api = Common.retrofit
        getUser()
    }

    private fun getUser() {
        api.getCurrentUser(accessToken)
            .enqueue(object : Callback<ResponseGetCurrentUser> {
                override fun onResponse(
                    call: Call<ResponseGetCurrentUser>,
                    response: Response<ResponseGetCurrentUser>
                ) {
                    val profileData = response.body()?.profileData
                    phone.text = profileData?.phone
                    username.text = profileData?.userName
                    city.text = profileData?.city
                    birthdayDate.text = profileData?.birthday.toString()
                    avatar.setImageURI(profileData?.avatar?.toUri())
                }

                override fun onFailure(call: Call<ResponseGetCurrentUser>, t: Throwable) {
                }
            })
    }

    private fun onClick(view: View?) {
        startActivity(
            Intent(this, EditProfileActivity::class.java)
                .putExtra("phone", phone.toString())
                .putExtra("username", username.toString())
                .putExtra("city", city.toString())
                .putExtra("birthday", birthdayDate.toString())
                .putExtra("avatar", avatar.drawable.toString())
                .putExtra("access_token", accessToken)
        )
    }
}

fun calculateZodiac(birthday: Date) {

}