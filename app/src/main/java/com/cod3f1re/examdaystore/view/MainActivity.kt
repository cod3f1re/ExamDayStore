package com.cod3f1re.examdaystore.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.cod3f1re.examdaystore.databinding.ActivityMainBinding
import com.cod3f1re.examdaystore.model.LocationAdapter
import com.cod3f1re.examdaystore.model.entities.AppDatabase
import com.cod3f1re.examdaystore.model.entities.Locations
import com.cod3f1re.examdaystore.model.entities.LocationsItem
import com.cod3f1re.examdaystore.view.location.LocationActivity
import com.cod3f1re.examdaystore.view.login.LoginActivity


/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
class MainActivity : AppCompatActivity() {

    //Contiene la vista inflada para acceder a los id
    private lateinit var binding: ActivityMainBinding
    //Email de sesion
    private var email:String?=null
    //Codigo de sesion
    private var code:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        getData()
        setContentView(view)
        setEvents()
    }


    private fun setEvents(){
        binding.btnSend.setOnClickListener {
            finish()
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            val sharedPreference = getSharedPreferences("ExamDayStore", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.apply()

            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getData(){
        var sharedPreference = getSharedPreferences("ExamDayStore", Context.MODE_PRIVATE)
        email=sharedPreference.getString("email","")
        code=sharedPreference.getInt("code",-1)
        binding.tvEmail.text = email
        binding.tvCode.text = code.toString()
    }
}
