package com.mauzerov.travelingsalesguyproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.mauzerov.travelingsalesguyproblem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewPager.apply {
            adapter = TabLayoutAdapter(supportFragmentManager, lifecycle).apply {
                
            }
            orientation = ViewPager2.ORIENTATION_VERTICAL
        }
    }
}
