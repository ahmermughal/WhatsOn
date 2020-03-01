package com.idevelopstudio.whatson.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false)

        val adapter = TopEventsAdapter()
        val smallEventAdapter = EventAdapter()

        binding.topEventsRecyclerView.adapter = adapter
        binding.newEventsRecyclerView.adapter = smallEventAdapter

        val list = ArrayList<Event>()
        list.add(
            Event(
                1,
                "Event 1",
                R.drawable.hiking
            )
        )
        list.add(
            Event(
                2,
                "Event 2",
                R.drawable.nigtclub
            )
        )
        list.add(
            Event(
                3,
                "Event 3",
                R.drawable.hiking
            )
        )


        adapter.submitList(list)
        smallEventAdapter.submitList(list)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
