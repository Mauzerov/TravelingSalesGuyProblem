package com.mauzerov.travelingsalesguyproblem.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = mutableListOf<Fragment>()
    fun addFragment(fragment : Fragment) {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}