package com.example.a05_frame_stack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


abstract class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

//  override fun getItem(position: Int): Fragment {
//    return when (position) {
//      0 -> {
//        FirstFragment()
//      }
////      else -> {
////        return ThirdFragment()
////      }
//      else -> {return SecondFragment()}
//    }
  }

//  override fun getCount(): Int {
//    return 2
//  }
//
//  override fun getPageTitle(position: Int): CharSequence {
//    return when (position) {
//      0 -> "First Tab"
//      1 -> "Second Tab"
//      else -> {
//        return "Third Tab"
//      }
//    }
//  }

//}

class FirstFragment : Fragment() {


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater!!.inflate(R.layout.activity_main, container, false)
  }

}

class SecondFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater!!.inflate(R.layout.activity_main, container, false)
  }

}