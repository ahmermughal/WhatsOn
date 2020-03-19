package com.idevelopstudio.whatson.eventDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

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

        val args = EventDetailFragmentArgs.fromBundle(arguments!!)
        binding.lifecycleOwner = this
        binding.event = args.selectedEvent
        binding.viewPager.adapter = SliderImageViewAdapter(args.selectedEvent.images)

        val ticketAdapter = TicketAdapter(TicketAdapter.OnClickListener{ticketType ->
            Toast.makeText(context, "Name: ${ticketType.ticket}, Price: ${ticketType.price}", Toast.LENGTH_SHORT).show()
        })

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
