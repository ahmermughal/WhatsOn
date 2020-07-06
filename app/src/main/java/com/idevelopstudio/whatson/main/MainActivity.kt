package com.idevelopstudio.whatson.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.ActivityMainBinding
import com.idevelopstudio.whatson.databinding.BottomSheetLayoutBinding
import com.idevelopstudio.whatson.home.HomeFragmentDirections
import com.idevelopstudio.whatson.models.Event

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var bottomSheetDialogBinding: BottomSheetLayoutBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupBottomNav()
        setupSearchBottomSheet()
    }

    private fun setupBottomNav() {
        navController = this.findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.profileFragment,
            R.id.bookingsFragment
        ).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_menu -> showSearchBottomSheet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchBottomSheet() {
        bottomSheetDialog = BottomSheetDialog(
            this,
            R.style.BottomSheetDialogTheme
        )
        bottomSheetDialogBinding = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)
        bottomSheetDialogBinding.viewModel = viewModel
        bottomSheetDialogBinding.lifecycleOwner = this
        bottomSheetDialogBinding.dismissButton.setOnClickListener {
            bottomSheetDialog.dismiss()
            bottomSheetDialogBinding.searchEditText.text = null
            viewModel.isNotSearching()
        }
        val adapter = SearchAdapter(clickListener)
        bottomSheetDialogBinding.searchRecyclerView.adapter = adapter
        bottomSheetDialogBinding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    viewModel.searchEventsByTitle(v.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    private val clickListener = SearchAdapter.OnClickListener { event: Event, i: Int ->
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(event))
        bottomSheetDialog.dismiss()
    }

    private fun showSearchBottomSheet() {

        bottomSheetDialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


}
