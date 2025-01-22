package com.odeniz.inohom.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.odeniz.inohom.databinding.ActivityMainBinding
import com.odeniz.inohom.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AuthViewModel by viewModels<AuthViewModel>()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            binding.loadingProgress.visibility = View.VISIBLE
            binding.errorText.visibility = View.GONE
            viewModel.authenticate("demo", "123456")
        }
        observeData()
    }

    private fun observeData() {
        viewModel.authState.observe(this) { response ->
            if (response.error == null) {
                binding.loadingProgress.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Authenticated!",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d(TAG, "Authenticated: ${response.params?.firstOrNull()}")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.loadingProgress.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = response.error
                Log.e(TAG, "Error: ${response.error}")
            }
        }
    }

}
