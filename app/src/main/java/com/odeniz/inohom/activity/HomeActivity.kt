package com.odeniz.inohom.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.odeniz.inohom.R
import com.odeniz.inohom.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment!!.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            setupBackButton(destination.id, navController)
        }

    }

    private fun setupBackButton(destinationId: Int, navController: NavController) {
        if (destinationId !in listOf(
                R.id.controlsFragment
            )
        ) {
            binding.backActionFragment.visibility = View.VISIBLE
            binding.backActionFragment.setOnClickListener {
                navController.popBackStack()
            }
        } else {
            binding.backActionFragment.visibility = View.GONE
        }
    }


}