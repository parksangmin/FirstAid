package com.sangmin.firstaid.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sangmin.firstaid.fragments.BoardFragment
import com.sangmin.firstaid.fragments.BookmarkFragment
import com.sangmin.firstaid.fragments.HomeFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> BookmarkFragment()
            else -> BoardFragment()
        }
    }
}