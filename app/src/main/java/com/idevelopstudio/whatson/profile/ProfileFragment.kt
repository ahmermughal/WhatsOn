package com.idevelopstudio.whatson.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentProfileBinding
import com.idevelopstudio.whatson.home.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProfileBinding.inflate(inflater)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.user.observe(viewLifecycleOwner, Observer {
//            if(it.gender == ""){
//                binding.genderImageView.visibility = View.INVISIBLE
//                binding.genderTextView.visibility = View.INVISIBLE
//            }else{
//                binding.genderTextView.text = it.gender
//            }

            if(it.phone == "0"){
                binding.phoneTextView.visibility = View.INVISIBLE
                binding.phoneImageView.visibility = View.INVISIBLE
            }else{
                binding.genderTextView.text = it.phone
            }
        })


        binding.user = FirebaseAuth.getInstance().currentUser!!

        return binding.root
    }


}
