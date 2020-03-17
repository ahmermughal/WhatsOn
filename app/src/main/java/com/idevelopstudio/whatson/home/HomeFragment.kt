package com.idevelopstudio.whatson.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.nameTextView.text = FirebaseAuth.getInstance().currentUser?.displayName

        binding.viewModel = viewModel
        binding.topEventsRecyclerView.adapter = TopEventsAdapter(TopEventsAdapter.OnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailFragment(it))
        })
        binding.newEventsRecyclerView.adapter = EventAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
