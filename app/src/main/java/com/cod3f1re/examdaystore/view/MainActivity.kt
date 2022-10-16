package com.cod3f1re.examdaystore.view

import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.cod3f1re.examdaystore.R
import com.cod3f1re.examdaystore.databinding.ActivityMainBinding
import com.cod3f1re.examdaystore.model.entities.AppDatabase
import com.cod3f1re.examdaystore.model.entities.Locations


class MainActivity : AppCompatActivity() {
    //Contiene la vista inflada para acceder a los id
    private lateinit var binding: ActivityMainBinding
    //Contiene la lista de ubicaciones anteriormente enviadas y guardadas
    var location: LiveData<List<Locations>>? = null
    //Email de sesion
    private var email:String?=null
    //Codigo de sesion
    private var code:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getLocationsFromRoom()
    }
    private fun getLocationsFromRoom(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "locations"
        ).build()
        val locationDAO = db.locationsDao()
        location = locationDAO.getLocations()
    }
    private fun getData(){
        val sharedPreference = getSharedPreferences("ExamDayStore", Context.MODE_PRIVATE)
        email=sharedPreference.getString("username","")
        code=sharedPreference.getInt("code",-1)

    }
}
