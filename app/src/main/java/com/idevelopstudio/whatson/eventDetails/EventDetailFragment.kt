package com.idevelopstudio.whatson.eventDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentEventDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class EventDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentEventDetailBinding.inflate(layoutInflater)

//        val list = listOf<String>("https://images.unsplash.com/photo-1558981001-792f6c0d5068?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80",
//            "https://images.unsplash.com/photo-1583608714736-348b31142a76?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=889&q=80",
//            "https://images.unsplash.com/photo-1583645870174-c14a9e54f34f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")

        val args = EventDetailFragmentArgs.fromBundle(arguments!!)

        Toast.makeText(context, "Event Name: ${args.selectedEvent.title}", Toast.LENGTH_SHORT).show()
        binding.lifecycleOwner = this
        binding.event = args.selectedEvent
        binding.viewPager.adapter = SliderImageViewAdapter(args.selectedEvent.images)

        val ticketAdapter = TicketAdapter()
        binding.selectTicketRecyclerView.adapter = ticketAdapter

        binding.selectDateRecyclerView.adapter = DateAdapter(DateAdapter.OnClickListener{
            ticketAdapter.submitList(it.ticketTypes)
        })

        binding.ticketAddButton.setOnClickListener {
            val numOfTickets = Integer.parseInt(binding.ticketCountTextView.text.toString())
            binding.ticketCountTextView.text = (numOfTickets+1).toString()
        }

        binding.ticketMinusButton.setOnClickListener {
            val numOfTickets = Integer.parseInt(binding.ticketCountTextView.text.toString())
            binding.ticketCountTextView.text = if (numOfTickets > 1) (numOfTickets-1).toString() else numOfTickets.toString()
        }

        return binding.root
    }



}
