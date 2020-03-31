package com.idevelopstudio.whatson.editProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.idevelopstudio.whatson.databinding.FragmentEditProfileBinding

/**
 * A simple [Fragment] subclass.
 */
class EditProfileFragment : Fragment() {

    val genders = listOf<String>("Male", "Female", "Other")

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var args: EditProfileFragmentArgs
    private val viewModel: EditProfileViewModel by lazy {
        ViewModelProvider(this).get(EditProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater)
        args = EditProfileFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "User Name is ${args.userName}", Toast.LENGTH_SHORT).show()
        binding.nameEditText.setText(args.userName)
        binding.emailEditText.setText(args.email)
        binding.ageEditText.setText(args.age.toString())
        binding.phoneEditText.setText(args.phone)

        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = adapter

        binding.updateProfileButton.setOnClickListener {
            updateProfile()
        }

        when(args.gender){
            "Male"-> binding.genderSpinner.setSelection(0)
            "Female" -> binding.genderSpinner.setSelection(1)
            "Other" -> binding.genderSpinner.setSelection(2)
            else-> binding.genderSpinner.setSelection(0)
        }

        viewModel.response.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment())
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            hideLoading()
            Toast.makeText(context, "Error, check internet and try again!", Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    fun updateProfile(){
        showLoading()
        viewModel.updateUser(args.uid,
            binding.nameEditText.text.toString(),
            genders[binding.genderSpinner.selectedItemPosition],
            binding.phoneEditText.text.toString(),
            Integer.valueOf(binding.ageEditText.text.toString()))
    }


    private fun showLoading(){
        binding.progressCircular.visibility = View.VISIBLE
        binding.updateProfileButton.visibility = View.INVISIBLE
    }

    private fun hideLoading(){
        binding.progressCircular.visibility = View.INVISIBLE
        binding.updateProfileButton.visibility = View.VISIBLE
    }
}
