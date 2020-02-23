package com.idevelopstudio.whatson.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.googleLoginButton.setOnClickListener {view:View ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }

        return binding.root
    }


}
