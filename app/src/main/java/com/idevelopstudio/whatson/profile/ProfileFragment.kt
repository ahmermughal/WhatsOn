package com.idevelopstudio.whatson.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.databinding.FragmentProfileBinding
import com.idevelopstudio.whatson.models.InterestsList

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
        viewModel.user.value?.let {

        }

        binding.editProfile.setOnClickListener {
            viewModel.user.value?.let {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(it.name,
                    it.email,
                    it.phone ?: "",
                    it.age ?: 0,
                    it.gender ?: "Male",
                    it.uid))
            }
        }

        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.phone == "0") {
                binding.phoneTextView.visibility = View.INVISIBLE
                binding.phoneImageView.visibility = View.INVISIBLE
            } else {
                binding.phoneTextView.visibility = View.VISIBLE
                binding.phoneImageView.visibility = View.VISIBLE
                binding.phoneTextView.text = it.phone
            }

            if (it.gender == "0") {
                binding.genderImageView.visibility = View.INVISIBLE
                binding.genderTextView.visibility = View.INVISIBLE
            } else {
                binding.genderTextView.visibility = View.VISIBLE
                binding.genderImageView.visibility = View.VISIBLE
                binding.genderTextView.text = it.gender
            }
        })

        binding.addInterestsButton.setOnClickListener {
            viewModel.user.value?.let {
                val interestList = InterestsList(it.userInterests)
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSelectInterestsFragment(interestList))
            }
        }

        binding.user = FirebaseAuth.getInstance().currentUser!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserData()
    }
}
