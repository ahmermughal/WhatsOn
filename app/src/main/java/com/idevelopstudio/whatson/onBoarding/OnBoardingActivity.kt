package com.idevelopstudio.whatson.onBoarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.ActivityOnBoardingBinding
import com.idevelopstudio.whatson.main.MainActivity
import com.idevelopstudio.whatson.models.OnBoarding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)
        supportActionBar!!.hide()

        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.SHARED_PREF_KEY),
            Context.MODE_PRIVATE
        )
        val sharedPrefEditor: SharedPreferences.Editor = sharedPreferences.edit()

        sharedPrefEditor.putInt(getString(R.string.SHARED_PREF_NEW_USER), 0)
        sharedPrefEditor.apply()
        initSetup()
        setupClickListeners()
    }

    private fun setupClickListeners(){
        binding.nextButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
    private fun initSetup(){
        val onBoardingList = listOf<OnBoarding>(
            OnBoarding("HELLO", getString(R.string.sampleString), applicationContext.getDrawable(R.drawable.welcoming)!!),
            OnBoarding("EVENT'S NEARBY", getString(R.string.sampleString), applicationContext.getDrawable(R.drawable.celebration)!!),
            OnBoarding("LET'S GO!", getString(R.string.sampleString), applicationContext.getDrawable(R.drawable.partying)!!)
        )

        val adapter = OnBoardingAdapter(onBoardingList)
        binding.viewPager.adapter = adapter

        binding.dotOne.setImageResource(R.drawable.shape_circle_accent)
        binding.dotTwo.setImageResource(R.drawable.shape_circle)
        binding.dotThree.setImageResource(R.drawable.shape_circle)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
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
