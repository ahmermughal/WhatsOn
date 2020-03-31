package com.idevelopstudio.whatson.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.Event

//@BindingAdapter("setImageViewSearchState")
//fun bindSearchStateToImageView(imageView: ImageView, searching:Boolean?){
//    searching?.let {
//        if (!searching){
//            imageView.visibility = View.VISIBLE
//            imageView.setImageResource(R.drawable.search_state)
//        }else{
//            imageView.visibility = View.INVISIBLE
//        }
//    }
//}
//
//@BindingAdapter("setRecyclerViewSearchState")
//fun bindSearchStateToRecyclerView(recyclerView: RecyclerView, searching: Boolean?){
//    searching?.let {
//        if (!searching){
//            recyclerView.visibility = View.INVISIBLE
//        }else{
//            recyclerView.visibility = View.VISIBLE
//        }
//    }
//}
//
//@BindingAdapter("setTextViewSearchState")
//fun bindSearchStateToTextView(textView: TextView, searching: Boolean?){
//    searching?.let {
//        if (!searching){
//            textView.text = "Enter event name to search."
//            textView.visibility = View.VISIBLE
//        }else{
//            textView.visibility = View.INVISIBLE
//        }
//    }
//}



@BindingAdapter("setSearchEventList")
fun bindSearchEventListToRecyclverView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let{
        val adapter = recyclerView.adapter as SearchAdapter
        adapter.submitList(it)
    }
}
