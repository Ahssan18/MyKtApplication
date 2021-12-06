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
import androidx.recyclerview.widget.LinearLayoutManager
import com.zebra.myktapplication.R
import com.zebra.myktapplication.adapter.AdapterAlarm
import com.zebra.myktapplication.databinding.FragmentAddAlarmBinding
import com.zebra.myktapplication.databinding.FragmentAlarmBinding
import com.zebra.myktapplication.room.RoomDb
import com.zebra.myktapplication.room.models.Alarm
import com.zebra.myktapplication.utility.Utility

class AlarmFragment : Fragment() {
    lateinit var binding: FragmentAlarmBinding
    lateinit var list: List<Alarm>
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(layoutInflater)
        clickListener()
        setAdapter()
        return binding.root

    }

    private fun setAdapter() {
        list = RoomDb.getInstance(requireActivity()).getAlarmDao().getAllAlarm()
        var adapter = AdapterAlarm(list)
        var linearlayoutManager = LinearLayoutManager(requireActivity())
        binding.recycleAlarm.layoutManager = linearlayoutManager
        binding.recycleAlarm.setAdapter(adapter)


    }


    private fun clickListener() {
        binding.tvAddAlarm.setOnClickListener {
            Utility.setState(true, it.context)
            Navigation.findNavController(it).navigate(R.id.action_alarmFragment_to_addAlarmFragment);
        }
    }


}