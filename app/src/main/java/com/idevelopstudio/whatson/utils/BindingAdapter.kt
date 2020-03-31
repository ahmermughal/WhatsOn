package com.idevelopstudio.whatson.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.home.EventAdapter
import com.idevelopstudio.whatson.home.HomeFragmentDirections
import com.idevelopstudio.whatson.home.TopEventsAdapter
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.models.EventList
import com.idevelopstudio.whatson.models.InterestsWithEvents
import timber.log.Timber

private const val BASE_URL="http://192.168.10.6:3000/"

@BindingAdapter("topEventListData")
fun bindTopEventRecyclerView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let{
        val adapter = recyclerView.adapter as TopEventsAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("eventListData")
fun bindEventRecyclerView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let{
        val adapter = recyclerView.adapter as EventAdapter
        adapter.submitList(it)
    }
}



@BindingAdapter("imageWithGlide")
fun bindImageViewWithGlide(imageView: ImageView, imageUrl : String?){
    imageUrl?.let {
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
    }
}

@BindingAdapter("serverImageWithGlide")
fun bindServerImageViewWithGlide(imageView: ImageView, imageUrl : String?){
    imageUrl?.let {
        val fixedImageUrl = BASE_URL+imageUrl
        Glide.with(imageView)
            .load(fixedImageUrl)
            .into(imageView)
    }
}

@BindingAdapter("eventImage")
fun ImageView.setEventBackground(item: Event?){
    item?.let {
        val imageUrl = BASE_URL+it.images[0]
        Timber.d("ImageUrl: ${it.images[0]}")
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}

@BindingAdapter("eventName")
fun TextView.setEventName(item: Event?){
    item?.let {
        setText(it.title)
    }
}

@BindingAdapter("setInterestEventList", "setInterestEventsCardNavController")
fun bingInterestEventsToRecyclerView(recyclerView: RecyclerView, interestsWithEvents: InterestsWithEvents?, navController: NavController?){
    interestsWithEvents?.let {
        val eventAdapter = EventAdapter(EventAdapter.OnClickListener{event->
            navController?.let {navController->
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(event))
            }
        })
        recyclerView.adapter = eventAdapter
        it.events?.let {list->
            eventAdapter.submitList(list)
        }
    }
}

@BindingAdapter("setBigCardEventList", "setBigCardNavController")
fun bindBigCardEventListToRV(recyclerView: RecyclerView, eventList: EventList?, navController: NavController?){
    eventList?.let {
        val topEventsAdapter = TopEventsAdapter(TopEventsAdapter.OnClickListener{event->
            navController?.let {navController->
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(event))
            }
        })
        recyclerView.adapter = topEventsAdapter
        topEventsAdapter.submitList(it.eventList)
    }
}


@BindingAdapter("setImageViewState")
fun bindStateToImageView(imageView: ImageView, state:State?){
    state?.let {
        when(state){
            State.NOT_EMPTY -> imageView.visibility = View.INVISIBLE
            State.EMPTY -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.empty_state)
            }
            State.NOT_SEARCHING -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.search_state)
            }
            State.LOADING-> imageView.visibility = View.INVISIBLE

        }
    }
}

@BindingAdapter("setRecyclerViewState")
fun bindStateToRecyclerView(recyclerView: RecyclerView, state: State?){
    state?.let {
        when(state){
            State.NOT_EMPTY -> recyclerView.visibility = View.VISIBLE
            State.EMPTY -> {
                recyclerView.visibility = View.INVISIBLE
            }
            State.NOT_SEARCHING -> {
                recyclerView.visibility = View.INVISIBLE
            }
            State.LOADING-> recyclerView.visibility = View.INVISIBLE

        }
    }
}

@BindingAdapter("setTextViewState")
fun bindStateToTextView(textView: TextView, state:State?){
    state?.let {
        when(state){
            State.NOT_EMPTY -> textView.visibility = View.INVISIBLE
            State.EMPTY -> {
                textView.visibility = View.VISIBLE
                textView.text = "Sorry, nothing found."
            }
            State.NOT_SEARCHING -> {
                textView.visibility = View.VISIBLE
                textView.text = "Enter event name in search bar to search."
            }
            State.LOADING-> textView.visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("setProgressBarState")
fun bindStateToProgressBar(progressBar: ProgressBar, state: State?){
    state?.let {
        when(state){
            State.NOT_EMPTY -> progressBar.visibility = View.INVISIBLE
            State.EMPTY ->     progressBar.visibility = View.INVISIBLE
            State.NOT_SEARCHING -> progressBar.visibility = View.INVISIBLE
            State.LOADING-> progressBar.visibility = View.VISIBLE
        }
    }
}