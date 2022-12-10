package com.mauzerov.travelingsalesguyproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mauzerov.travelingsalesguyproblem.adapter.TabLayoutAdapter
import com.mauzerov.travelingsalesguyproblem.databinding.ActivityMainBinding
import com.mauzerov.travelingsalesguyproblem.fragments.CitiesFragment
import com.mauzerov.travelingsalesguyproblem.fragments.ConnectionsFragment
import com.mauzerov.travelingsalesguyproblem.fragments.TSPFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tabLayout.apply {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })
        }
        binding.viewPager.apply {
            adapter = TabLayoutAdapter(supportFragmentManager, lifecycle).apply {
                addFragment(TSPFragment.newInstance(mainActivityViewModel))
                addFragment(CitiesFragment.newInstance(mainActivityViewModel))
                addFragment(ConnectionsFragment.newInstance(mainActivityViewModel))
            }
            orientation = ViewPager2.ORIENTATION_HORIZONTAL

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                }
            })
        }
    }
}
