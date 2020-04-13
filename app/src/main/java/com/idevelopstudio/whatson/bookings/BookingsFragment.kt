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

    private val viewModel: BookingsViewModel by lazy {
        BookingsViewModelFactory(FirebaseAuth.getInstance().uid!!).create(BookingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookingsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = BookingsAdapter(BookingsAdapter.OnClickListener{
            findNavController().navigate(BookingsFragmentDirections.actionBookingsFragmentToBookingDetailsFragment(it))
        })
        binding.bookingsRecyclerView.adapter = adapter

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        viewModel.getAllBookingsOf(FirebaseAuth.getInstance().uid!!)
    }
}
