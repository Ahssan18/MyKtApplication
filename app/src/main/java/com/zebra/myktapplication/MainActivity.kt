package com.zebra.myktapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zebra.myktapplication.R
import com.zebra.myktapplication.adapter.AdpaterTwo
import com.zebra.myktapplication.databinding.ActivityMainBinding
import org.jetbrains.annotations.Contract

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
   lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        clickListeners()
        setContentView(binding.root)
    }

    private fun clickListeners() {

    }
}