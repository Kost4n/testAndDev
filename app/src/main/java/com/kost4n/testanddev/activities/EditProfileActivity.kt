package com.kost4n.testanddev.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.kost4n.testanddev.R
import com.kost4n.testanddev.api.Common
import com.kost4n.testanddev.api.ResponseGetCurrentUser
import com.kost4n.testanddev.api.UsersApi
import com.kost4n.testanddev.api.data.Avatars
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity: AppCompatActivity() {
    private lateinit var avatar: ImageView
    private lateinit var phone: TextView
    private lateinit var username: TextView
    private lateinit var city: EditText
    private lateinit var birthdayDate: EditText
    private lateinit var zodiac: TextView
    private lateinit var about: EditText
    private lateinit var buttonEnd: Button
    private lateinit var buttonAvatar: Button
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
        buttonEnd = findViewById(R.id.button_profile)
        buttonEnd.setOnClickListener { v -> onClickEnd(v) }
        buttonAvatar = findViewById(R.id.avatar_button)
        accessToken = intent.extras?.getString("access_token").toString()

        api = Common.retrofit
    }




    private fun onClickEnd(view: View) {
        api.updateUser(accessToken, birthdayDate.toString(), city.toString())
    }

    fun getUser() {
        val extras = intent.extras
        phone.text = extras?.getString("phone")
        username.text = extras?.getString("username")
        city.setText(extras?.getString("city"))
        about.setText(extras?.getString("about"))
        birthdayDate.setText(extras?.getString("birthday"))
        avatar.setImageURI(extras?.getString("avatar")?.toUri())
    }
}