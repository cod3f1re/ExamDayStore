package com.cod3f1re.examdaystore.view.Location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cod3f1re.examdaystore.databinding.ActivityLocationBinding


class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}