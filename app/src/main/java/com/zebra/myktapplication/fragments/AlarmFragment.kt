package com.zebra.myktapplication.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.zebra.myktapplication.R
import com.zebra.myktapplication.databinding.FragmentAddAlarmBinding
import com.zebra.myktapplication.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {
    lateinit var binding:FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAlarmBinding.inflate(layoutInflater)
        clickListener()
        return binding.root

    }



    private fun clickListener() {
        binding.tvAddAlarm.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_alarmFragment_to_addAlarmFragment);
        }
    }


}