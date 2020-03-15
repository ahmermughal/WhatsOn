package com.idevelopstudio.whatson.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.idevelopstudio.whatson.databinding.ActivityMainBinding
import com.idevelopstudio.whatson.databinding.BottomSheetLayoutBinding
import com.idevelopstudio.whatson.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var  appBarConfiguration: AppBarConfiguration
    private lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main
            )
        navController = this.findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.loginFragment,
            R.id.homeFragment,
            R.id.profileFragment,
            R.id.bookingsFragment
        ).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id){
                R.id.loginFragment -> {
                    hideBottomNav()
                    supportActionBar!!.hide()
                }
                else -> {
                    showBottomNav()
                    supportActionBar!!.show()
                }
            }
        }


    }

    private fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_menu -> showSearchBottomSheet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearchBottomSheet(){
        val bottomSheetDialog = BottomSheetDialog(this,
            R.style.BottomSheetDialogTheme
        )
        //val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, bottomSheetContainer)

        val binding: BottomSheetLayoutBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.bottom_sheet_layout, bottomSheetContainer, false)

        bottomSheetDialog.setContentView(binding.root)

//
//        val adapter = SearchAdapter()
//        val list = ArrayList<Event>()
//        list.add(
//            Event(
//                "1",
//                "Event 1",
//                R.drawable.hiking
//            )
//        )
//        list.add(
//            Event(
//                "2",
//                "Event 2",
//                R.drawable.nigtclub
//            )
//        )
//        list.add(
//            Event(
//                "3",
//                "Event 3",
//                R.drawable.hiking
//            )
//        )
//        list.add(
//            Event(
//                "4",
//                "Event 4",
//                R.drawable.nigtclub
//            )
//        )
//        list.add(
//            Event(
//                "5",
//                "Event 5",
//                R.drawable.loginbg
//            )
//        )
//
//        adapter.submitList(list)
//
//        binding.searchRecyclerView.adapter = adapter

        bottomSheetDialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


}
