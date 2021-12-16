package com.zebra.myktapplication.fragments

import android.R
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Dao
import com.google.gson.Gson
import com.zebra.myktapplication.databinding.FragmentAddAlarmBinding
import com.zebra.myktapplication.room.RoomDb
import com.zebra.myktapplication.room.dao.AlarmDaos
import com.zebra.myktapplication.room.models.Alarm
import com.zebra.myktapplication.utility.Utility


class AddAlarmFragment : Fragment() {
    lateinit var binding: FragmentAddAlarmBinding
    var navController: NavController? = null
    var alarm = "only"
    var switchStatus = true
    lateinit var dao: AlarmDaos
    lateinit var model: Alarm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddAlarmBinding.inflate(inflater)
        dao = RoomDb.getInstance(requireActivity())!!.getAlarmDao()
        navController = Navigation.findNavController(
            requireActivity(),
            com.zebra.myktapplication.R.id.nav_host_fragment_container
        )
        clickListener()

        return binding.root
    }

    override fun onResume() {

        try {
            if (arguments != null) {
                if (requireArguments().containsKey("data")) {
                    model = requireArguments().getParcelable<Alarm>("data")!!
                    Utility.setModel(model.toString(), requireActivity())
                    binding.tvSchedule.text = model.schedule
                    binding.tvEdit.text = "EditAlarm"
                    binding.btnDelAlarm.visibility = View.VISIBLE
                } else {
                    alarm = requireArguments()!!.getString("alarm").toString()
                    binding.tvSchedule.text = alarm
                }
            }
            if (Utility.getState(requireContext())) {
                var modelString = Utility.getModel(requireActivity())
                var gson = Gson()
                model = gson.fromJson(modelString, Alarm::class.java)
                var time = model.time
                var arr = time.split(":")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.timePicker.hour = arr[0].toInt()
                    binding.timePicker.minute = arr[1].toInt()
                } else {
                    binding.timePicker.currentHour = arr[0].toInt()
                    binding.timePicker.currentMinute = arr[1].toInt()
                }
                binding.timePicker.setIs24HourView(false)
                alarm = model.schedule
                binding.switchVib.setChecked(model.alarmstatus)
            }
        } catch (e: Exception) {
        }
        super.onResume()
    }

    private fun clickListener() {
        binding.switchVib.setOnCheckedChangeListener { _, isChecked ->
            switchStatus = isChecked
        }
        binding.frameRepeat.setOnClickListener {
            navController!!.navigate(com.zebra.myktapplication.R.id.action_addAlarmFragment_to_itemListDialogFragment)
        }
        binding.btnGettime.setOnClickListener {
            var hour: Int
            var minute: Int
            var am_pm: String;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = binding.timePicker.hour
                minute = binding.timePicker.minute
            } else {
                hour = binding.timePicker.currentHour
                minute = binding.timePicker.currentMinute
            }
            if (hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            } else {
                am_pm = "AM";
            }
            var alarm = Alarm("$hour:$minute", am_pm, alarm, switchStatus, false)

            if (Utility.getState(requireContext())) {
                dao.update(alarm)
            } else {
                dao.add(alarm)
            }
            Utility.setState(false, requireContext())
            navController!!.navigate(com.zebra.myktapplication.R.id.alarmFragment)
        }
        binding.btnDelAlarm.setOnClickListener {
            dao.deleteAlarm(model)
            Navigation.findNavController(it).navigate(com.zebra.myktapplication.R.id.alarmFragment)
        }
        binding.timePicker.setOnClickListener {
            var hour: Int
            var minute: Int
            var am_pm: String;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = binding.timePicker.hour
                minute = binding.timePicker.minute
            } else {
                hour = binding.timePicker.currentHour
                minute = binding.timePicker.currentMinute
            }
            if (hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            } else {
                am_pm = "AM";
            }
            Log.e("TAG", "$hour:$minute $am_pm")

            Toast.makeText(activity, "$hour:$minute $am_pm", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        navController = null
        super.onDestroy()
    }


}