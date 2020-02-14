package com.idevelopstudio.whatson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.idevelopstudio.whatson.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment,
                                                            R.id.profileFragment,
                                                            R.id.bookingsFragment).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)


        binding.bottomNav.setupWithNavController(navController)



    }


}
