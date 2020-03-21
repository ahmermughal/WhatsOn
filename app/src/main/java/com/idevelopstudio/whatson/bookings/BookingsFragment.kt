package com.idevelopstudio.whatson.bookings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentBookingsBinding
import com.idevelopstudio.whatson.home.TopEventsAdapter

/**
 * A simple [Fragment] subclass.
 */
class BookingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookingsBinding.inflate(inflater)

        val viewModelFactory = BookingsViewModelFactory(FirebaseAuth.getInstance().uid!!)

        val viewModel = viewModelFactory.create(BookingsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = BookingsAdapter(BookingsAdapter.OnClickListener{
            findNavController().navigate(BookingsFragmentDirections.actionBookingsFragmentToBookingDetailsFragment(it))
        })
        binding.bookingsRecyclerView.adapter = adapter

        return binding.root
    }


}
