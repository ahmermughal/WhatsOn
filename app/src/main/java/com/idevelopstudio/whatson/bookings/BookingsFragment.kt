package com.idevelopstudio.whatson.bookings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        val adapter = TopEventsAdapter(TopEventsAdapter.OnClickListener{

        })

        return binding.root
    }


}
