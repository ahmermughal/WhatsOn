package com.idevelopstudio.whatson


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.idevelopstudio.whatson.databinding.FragmentBookingsBinding

/**
 * A simple [Fragment] subclass.
 */
class BookingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookings, container, false)
        return binding.root
    }


}
