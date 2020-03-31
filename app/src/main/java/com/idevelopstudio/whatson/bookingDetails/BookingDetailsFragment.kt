package com.idevelopstudio.whatson.bookingDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.databinding.FragmentBookingDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class BookingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBookingDetailsBinding
    private lateinit var args: BookingDetailsFragmentArgs

    private val viewModel: BookingDetailsViewModel by lazy {
        ViewModelProvider(this).get(BookingDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingDetailsBinding.inflate(layoutInflater)
        args = BookingDetailsFragmentArgs.fromBundle(arguments!!)
        binding.event = args.userBooking.event
        binding.bookingDetails = args.userBooking.bookingDetails


        viewModel.response.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            findNavController().navigate(BookingDetailsFragmentDirections.actionBookingDetailsFragmentToBookingsFragment())
        })

        binding.cancelReservationButton.setOnClickListener {
            viewModel.deleteBooking(FirebaseAuth.getInstance().uid!!, args.userBooking.id)
        }

        return binding.root
        //TODO - Add Barcode for booking id
    }

}
