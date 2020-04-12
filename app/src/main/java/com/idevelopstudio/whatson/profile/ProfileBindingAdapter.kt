package com.idevelopstudio.whatson.profile

import android.view.View
import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.idevelopstudio.whatson.models.Interest
import com.idevelopstudio.whatson.utils.getImageByTitle
import timber.log.Timber

@BindingAdapter("setInterestNumber", "setupUserInterestImage")
fun bindUserInterestToImageButton(imageButton: ImageButton,number: Int, interestList: List<Interest>?){
    interestList?.let {
        if(number < (interestList.size)){
            imageButton.visibility = View.VISIBLE
            imageButton.setImageResource(getImageByTitle(interestList[number].title))
        }else{
            imageButton.visibility = View.GONE
        }
    }
}