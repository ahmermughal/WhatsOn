package com.idevelopstudio.whatson.onBoarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.FragmentOnBoardingBinding
import com.idevelopstudio.whatson.models.OnBoarding

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentOnBoardingBinding.inflate(inflater)

        val sharedPreferences = activity!!.getSharedPreferences(
            getString(R.string.SHARED_PREF_KEY),
            Context.MODE_PRIVATE
        )
        val sharedPrefEditor: SharedPreferences.Editor = sharedPreferences.edit()

        sharedPrefEditor.putInt(getString(R.string.SHARED_PREF_NEW_USER), 0)
        sharedPrefEditor.apply()
        initSetup()
        setupClickListeners()
        return binding.root
    }


    private fun setupClickListeners(){
        binding.nextButton.setOnClickListener {
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment())
        }
    }

    private fun initSetup(){
        val onBoardingList = listOf<OnBoarding>(OnBoarding("HELLO", getString(R.string.sampleString), context!!.getDrawable(R.drawable.welcoming)!!),
            OnBoarding("EVENT'S NEARBY", getString(R.string.sampleString), context!!.getDrawable(R.drawable.celebration)!!),
            OnBoarding("LET'S GO!", getString(R.string.sampleString), context!!.getDrawable(R.drawable.partying)!!))

        val adapter = OnBoardingAdapter(onBoardingList)
        binding.viewPager.adapter = adapter

        binding.dotOne.setImageResource(R.drawable.shape_circle_accent)
        binding.dotTwo.setImageResource(R.drawable.shape_circle)
        binding.dotThree.setImageResource(R.drawable.shape_circle)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position){
                    0 -> {
                        binding.dotOne.setImageResource(R.drawable.shape_circle_accent)
                        binding.dotTwo.setImageResource(R.drawable.shape_circle)
                        binding.dotThree.setImageResource(R.drawable.shape_circle)
                        binding.nextButton.isEnabled = false
                    }
                    1 -> {
                        binding.dotOne.setImageResource(R.drawable.shape_circle)
                        binding.dotTwo.setImageResource(R.drawable.shape_circle_accent)
                        binding.dotThree.setImageResource(R.drawable.shape_circle)
                        binding.nextButton.isEnabled = false
                    }
                    2->{
                        binding.dotOne.setImageResource(R.drawable.shape_circle)
                        binding.dotTwo.setImageResource(R.drawable.shape_circle)
                        binding.dotThree.setImageResource(R.drawable.shape_circle_accent)
                        binding.nextButton.isEnabled = true
                    }
                }
            }
        })
    }
}
