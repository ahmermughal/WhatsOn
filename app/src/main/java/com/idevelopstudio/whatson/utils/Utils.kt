package com.idevelopstudio.whatson.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.idevelopstudio.whatson.R

fun getImageByTitle(name: String): Int {
    when (name) {
        "Group"-> return R.drawable.group
        "Music"-> return R.drawable.ic_music
        "Party"-> return R.drawable.ic_close
        "Art"-> return R.drawable.art
        "Drinks"-> return R.drawable.drink
        "Candlelight Concerts"-> return R.drawable.concert
        "Theater"-> return R.drawable.theater
        "Food"-> return R.drawable.food
        "Bars"-> return R.drawable.bars
        "Couple"-> return R.drawable.couple
        "Cinema"-> return R.drawable.cinema
        "Afterwork"-> return R.drawable.ic_close
        "Single"-> return R.drawable.single
        "Outdoors"-> return R.drawable.ic_close
        "Fashion"-> return R.drawable.ic_close
        "Sport"-> return R.drawable.ic_close
        "Family"-> return R.drawable.ic_close
        "Comedy"-> return R.drawable.ic_close
        "Wellness"-> return R.drawable.ic_close
        "Popup"-> return R.drawable.ic_close
        "Festival"-> return R.drawable.ic_close
        "Brunch"-> return R.drawable.ic_close
        "Charity"-> return R.drawable.ic_close
        "LGBTQ+"-> return R.drawable.ic_close
        else -> return R.drawable.ic_close
    }
}