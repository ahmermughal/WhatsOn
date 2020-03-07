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
        val binding: FragmentBookingsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_bookings, container, false)

//        val adapter = TopEventsAdapter()
//
//        binding.bookingsRecyclerView.adapter = adapter
//
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
//                "1",
//                "Event 2",
//                R.drawable.nigtclub
//            )
//        )
//        list.add(
//            Event(
//                "1",
//                "Event 3",
//                R.drawable.hiking
//            )
//        )
//        list.add(
//            Event(
//                "1",
//                "Event 4",
//                R.drawable.nigtclub
//            )
//        )
//
//        adapter.submitList(list)

        return binding.root
    }


}
