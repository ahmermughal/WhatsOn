package com.idevelopstudio.whatson.eventDetails

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.DialogTicketConfirmBinding
import com.idevelopstudio.whatson.databinding.FragmentEventDetailBinding
import com.idevelopstudio.whatson.models.EventDate
import com.idevelopstudio.whatson.models.TicketType
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 */
class EventDetailFragment : Fragment() {

    private lateinit var viewModelFactory: EventDetailViewModelFactory
    private lateinit var args: EventDetailFragmentArgs
    private lateinit var currentEventDate : EventDate
    private val eventViewModel: EventDetailViewModel by lazy {
        viewModelFactory.create(EventDetailViewModel::class.java)
    }

    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailBinding.inflate(layoutInflater)
        args = EventDetailFragmentArgs.fromBundle(arguments!!)
        currentEventDate = args.selectedEvent.days!![0]
        val currentTicket = args.selectedEvent.days!![0].ticketTypes[0]
        binding.lifecycleOwner = this
        binding.event = args.selectedEvent
        binding.viewPager.adapter = SliderImageViewAdapter(args.selectedEvent.images)
        viewModelFactory = EventDetailViewModelFactory(currentTicket)
        binding.viewModel = eventViewModel

        val ticketAdapter = TicketAdapter(TicketAdapter.OnClickListener{ticketType ->
            eventViewModel.changeTicket(ticketType)
        })
        binding.selectTicketRecyclerView.adapter = ticketAdapter

        binding.selectDateRecyclerView.adapter = DateAdapter(DateAdapter.OnClickListener{
            ticketAdapter.submitList(it.ticketTypes)
            currentEventDate = it
        })

        setupListenersAndObservers()

        return binding.root
    }

    private fun setTicketPriceToButton(price: Double){
        val decFormat = DecimalFormat("##.00")
        binding.buyTicketButton.text = "${decFormat.format(price)} AED-Reserve Now"
    }

    private fun showConfirmationDialog(){
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bindingDialog = DialogTicketConfirmBinding.inflate(layoutInflater)
        bindingDialog.lifecycleOwner = viewLifecycleOwner
        dialog.setContentView(bindingDialog.root)
        bindingDialog.viewModel = eventViewModel
        bindingDialog.event = args.selectedEvent
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes!!);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        bindingDialog.reserveTicketButton.setOnClickListener {
            eventViewModel.confirmBooking(FirebaseAuth.getInstance().uid!!, args.selectedEvent.id, currentEventDate.id)
            dialog.dismiss()
        }

        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun setupListenersAndObservers(){
        binding.ticketAddButton.setOnClickListener {
            eventViewModel.incrementTicketCount()
        }
        binding.ticketMinusButton.setOnClickListener {
            eventViewModel.decrementTicketCount()

        }

        binding.buyTicketButton.setOnClickListener {
            showConfirmationDialog()
        }

        eventViewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            setTicketPriceToButton(it)
        })

        eventViewModel.response.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

        eventViewModel.error.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context, "Error Booking Ticket, Please Try Again.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
