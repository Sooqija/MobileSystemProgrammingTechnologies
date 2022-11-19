package com.example.a05_frame_stack

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fm: FragmentManager,
                       lifecycle: Lifecycle,
                       public var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putString("fragmentName", "Music Fragment")
                val musicFragment = MainFragment()
                musicFragment.arguments = bundle
                return musicFragment
            }
            1 -> {
                // # Movies Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Movies Fragment")
                val moviesFragment = AttachedFragment()
                moviesFragment.arguments = bundle
                return moviesFragment
            }
            else -> return AttachedFragment()

            //            2 -> {
//                // # Books Fragment
//                val bundle = Bundle()
//                bundle.putString("fragmentName", "Books Fragment")
////                val booksFragment = DemoFragment()
////                booksFragment.arguments = bundle
////                return booksFragment
//            }

//        return MainFragment()

        }
    }
        override fun getItemCount(): Int {
            return numberOfTabs
        }
//    }
}