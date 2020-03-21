package com.idevelopstudio.whatson.bookingDetails

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.idevelopstudio.whatson.models.BookingDetails
import java.text.DecimalFormat

@BindingAdapter("setNameWithCount")
fun bindTicketDetailsToTextView(textView: TextView, bookingDetails: BookingDetails){
    val string = "${bookingDetails.ticketDetails} x ${bookingDetails.ticketBooked}"
    textView.text = string
}

@BindingAdapter("setPriceWithBookingDetails")
fun bindpriceToTextViewWithBookingDetails(textView: TextView, bookingDetails: BookingDetails){
    val price = bookingDetails.ticketPrice*bookingDetails.ticketBooked
    val decFormat = DecimalFormat("##.00")
    textView.text = "${decFormat.format(price)} AED"
}