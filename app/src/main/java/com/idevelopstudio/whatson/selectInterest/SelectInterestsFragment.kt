package com.idevelopstudio.whatson.selectInterest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentSelectInterestsBinding
import com.idevelopstudio.whatson.home.HomeViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class SelectInterestsFragment : Fragment() {

    private val viewModel: SelectInterestsViewModel by lazy {
        ViewModelProvider(this).get(SelectInterestsViewModel::class.java)
    }
    private lateinit var args: SelectInterestsFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args = SelectInterestsFragmentArgs.fromBundle(arguments!!)
        val interestList = args.interestList
        val binding = FragmentSelectInterestsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val layoutManager = GridLayoutManager(context, 4)
        binding.interestsRecyclerView.layoutManager = layoutManager
        val adapter = InterestsAdapter()
        binding.interestsRecyclerView.adapter = adapter
        adapter.submitList(interestList.interestList)
        binding.saveButton.setOnClickListener {
            val list = adapter.returnList()
            viewModel.updateInterestList(list)
        }
        return binding.root
    }

}
