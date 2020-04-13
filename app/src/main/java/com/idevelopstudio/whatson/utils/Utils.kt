package com.idevelopstudio.whatson.utils

import com.idevelopstudio.whatson.R

fun getImageByTitle(name: String): Int {
    when (name) {
        "Group"-> return R.drawable.group
        "Music"-> return R.drawable.ic_music
        "Party"-> return R.drawable.party
        "Art"-> return R.drawable.art
        "Drinks"-> return R.drawable.drink
        "Candlelight Concerts"-> return R.drawable.concert
        "Theater"-> return R.drawable.theater
        "Food"-> return R.drawable.food
        "Bars"-> return R.drawable.bars
        "Couple"-> return R.drawable.couple
        "Cinema"-> return R.drawable.cinema
        "Afterwork"-> return R.drawable.afterwok
        "Single"-> return R.drawable.single
        "Outdoors"-> return R.drawable.outdoors
        "Fashion"-> return R.drawable.fashion
        "Sport"-> return R.drawable.sport
        "Family"-> return R.drawable.family
        "Comedy"-> return R.drawable.comedy
        "Wellness"-> return R.drawable.wellness
        "Popup"-> return R.drawable.popup
        "Festival"-> return R.drawable.festival
        "Brunch"-> return R.drawable.brunch
        "Charity"-> return R.drawable.charity
        "LGBTQ+"-> return R.drawable.lgbtq
        else -> return R.drawable.ic_close
    }
}