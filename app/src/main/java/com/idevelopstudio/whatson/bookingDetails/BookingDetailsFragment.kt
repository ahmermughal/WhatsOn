package com.idevelopstudio.whatson.bookingDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.idevelopstudio.whatson.databinding.FragmentBookingDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class BookingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBookingDetailsBinding
    private lateinit var args: BookingDetailsFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingDetailsBinding.inflate(layoutInflater)
        args = BookingDetailsFragmentArgs.fromBundle(arguments!!)
        binding.event = args.userBooking.event
        binding.bookingDetails = args.userBooking.bookingDetails
        return binding.root
        //TODO - Add Barcode for booking id
    }

}
