package com.idevelopstudio.whatson.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.databinding.FragmentHomeBinding
import com.idevelopstudio.whatson.models.InterestsWithEvents

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private lateinit var adapter: HomeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.nameTextView.text = FirebaseAuth.getInstance().currentUser?.displayName
        binding.viewModel = viewModel

        viewModel.topEvents.observe(viewLifecycleOwner, Observer {
            adapter = HomeRecyclerViewAdapter(it, findNavController())
            binding.eventsRecyclerView.adapter = adapter
        })

        viewModel.interestsWithEvents.observe(viewLifecycleOwner, Observer {
            adapter.submitListOfInterestsWithEvents(it)
        })
        return binding.root
    }

}


