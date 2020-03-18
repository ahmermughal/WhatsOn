package com.idevelopstudio.whatson.selectInterest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentSelectInterestsBinding

/**
 * A simple [Fragment] subclass.
 */
class SelectInterestsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSelectInterestsBinding.inflate(inflater)

        val layoutManager = GridLayoutManager(context, 4)
        binding.interestsRecyclerView.layoutManager = layoutManager

        return binding.root
    }

}
