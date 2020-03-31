package com.idevelopstudio.whatson.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ListItemBigEventRecyclerViewBinding
import com.idevelopstudio.whatson.databinding.ListItemEventRecyclerViewBinding
import com.idevelopstudio.whatson.models.EventList
import com.idevelopstudio.whatson.models.InterestsWithEvents

class HomeRecyclerViewAdapter(private val eventList: EventList, val navController: NavController) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var listOfInterestsWithEvents: List<InterestsWithEvents> = emptyList()

    companion object {
        private const val TYPE_BIG_EVENT_RECYCLER_VIEW = 0
        private const val TYPE_SMALL_EVENT_RECYCLER_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_BIG_EVENT_RECYCLER_VIEW -> BigEventRecyclerViewHolder.from(parent)
            else -> {
                SmallEventRecyclerViewHolders.from(parent)
            }
        }
    }

    fun submitListOfInterestsWithEvents(listOfInterestsWithEvents: List<InterestsWithEvents>) {
        this.listOfInterestsWithEvents = listOfInterestsWithEvents
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (listOfInterestsWithEvents.isEmpty()) {
            1
        } else {
            listOfInterestsWithEvents.size + 1
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is BigEventRecyclerViewHolder -> holder.bind(eventList, navController)
            is SmallEventRecyclerViewHolders -> holder.bind(
                listOfInterestsWithEvents[position - 1],
                navController
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_BIG_EVENT_RECYCLER_VIEW
            else -> {TYPE_SMALL_EVENT_RECYCLER_VIEW}
        }
    }

    class BigEventRecyclerViewHolder private constructor(val binding: ListItemBigEventRecyclerViewBinding) :
        BaseViewHolder<EventList>(binding) {
        override fun bind(item: EventList, navController: NavController) {
            binding.events = item
            binding.navController = navController
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BigEventRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemBigEventRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return BigEventRecyclerViewHolder(binding)
            }
        }
    }

    class SmallEventRecyclerViewHolders private constructor(val binding: ListItemEventRecyclerViewBinding) :
        BaseViewHolder<InterestsWithEvents>(binding) {
        override fun bind(item: InterestsWithEvents, navController: NavController) {
            binding.interestsWithEvent = item
            if (item.events.isNullOrEmpty()) {
                binding.newEventsRecyclerView.visibility = View.GONE
                binding.emptyStateTextView.visibility = View.VISIBLE
                binding.newEventsTextView.visibility = View.VISIBLE
            } else {
                item.events?.let {
                    binding.newEventsRecyclerView.visibility = View.VISIBLE
                    binding.newEventsTextView.visibility = View.VISIBLE
                    binding.emptyStateTextView.visibility = View.GONE
                    binding.navController = navController
                    binding.executePendingBindings()
                }
            }
        }
        companion object {
            fun from(parent: ViewGroup): SmallEventRecyclerViewHolders {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemEventRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return SmallEventRecyclerViewHolders(binding)
            }
        }
    }

}


abstract class BaseViewHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T, navController: NavController)
}