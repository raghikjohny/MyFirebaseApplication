package com.example.myfirebaseapplication.registration.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.ProjectEventListners
import com.example.myfirebaseapplication.databinding.ActivityRegistrationBinding
import com.example.myfirebaseapplication.home.view.HomeActivity
import com.example.myfirebaseapplication.login.view.LoginActivity
import com.example.myfirebaseapplication.model.User
import com.example.myfirebaseapplication.registration.viewmodel.RegistrationViewModel


class RegistrationActivity : AppCompatActivity() {
    lateinit var viewModel: RegistrationViewModel
    lateinit var binding: ActivityRegistrationBinding
    private lateinit var user: User
    var pic_id: Int = 333
    private lateinit var photo: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        doSignUp()
        observeUser()
    }

    private fun observeUser() {
        viewModel.userLiveData.observe(this@RegistrationActivity, Observer {
            if (it != null) {
                Toast.makeText(this, getString(R.string.user_error), Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        })
        viewModel.error.observe(this@RegistrationActivity, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun doSignUp() {
        binding.eventListener = object : ProjectEventListners.SignUpEvents {
            override fun signUp() {
                user = User(
                    binding.etName.text.toString(),
                    binding.etBiography.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etEmail.text.toString(),
                    photo.toString()
                )
                viewModel.doSingUp(user)
            }
            override fun takeImage() {
                val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camera_intent, pic_id)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        photo = (data!!.extras!!["data"] as Bitmap?)!!
        binding.ivProfile.setImageBitmap(photo)
    }
}
