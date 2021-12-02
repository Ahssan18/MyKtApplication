package com.zebra.myktapplication.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zebra.myktapplication.databinding.FragmentAddAlarmBinding

class AddAlarmFragment : Fragment() {
    lateinit var binding:FragmentAddAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddAlarmBinding.inflate(inflater)
        clickListener()
        return binding.root
    }



    private fun clickListener() {
        binding.btnGettime.setOnClickListener{
            var hour:Int
            var minute:Int
            var am_pm:String;
            if (Build.VERSION.SDK_INT >= 23 ){
                hour = binding.timePicker.hour
                minute = binding.timePicker.minute
            }
            else{
                hour = binding.timePicker.currentHour
                minute = binding.timePicker.currentMinute
            }
            if(hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            }
            else
            {
                am_pm="AM";
            }
            Log.e("TAG","$hour:$minute $am_pm")
            Toast.makeText(activity,"$hour:$minute $am_pm",Toast.LENGTH_LONG).show()
        }
        binding.timePicker.setOnClickListener{
            var hour:Int
            var minute:Int
            var am_pm:String;
            if (Build.VERSION.SDK_INT >= 23 ){
                hour = binding.timePicker.hour
                minute = binding.timePicker.minute
            }
            else{
                hour = binding.timePicker.currentHour
                minute = binding.timePicker.currentMinute
            }
            if(hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            }
            else
            {
                am_pm="AM";
            }
            Log.e("TAG","$hour:$minute $am_pm")
           Toast.makeText(activity,"$hour:$minute $am_pm",Toast.LENGTH_LONG).show()
        }
        }


}