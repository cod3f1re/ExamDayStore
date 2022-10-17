package com.cod3f1re.examdaystore.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cod3f1re.examdaystore.databinding.ActivityMainBinding
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
        //Evento para abrir la interfaz del mapa y poder enviar la ubicación
        binding.btnSend.setOnClickListener {
            finish()
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
        //Evento para cerrar la sesion y mandar al login
        binding.btnLogout.setOnClickListener {
            val sharedPreference = getSharedPreferences("ExamDayStore", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.apply()

            //Se manda al login y se limpia el stack de activities que pueda haber
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    /**
     * Metodo que obtiene los datos guardados en shared preference, que unicamente son el codigo del endpoint
     * y el email que introdujeron
     */
    private fun getData(){
        var sharedPreference = getSharedPreferences("ExamDayStore", Context.MODE_PRIVATE)
        email=sharedPreference.getString("email","")
        code=sharedPreference.getInt("code",-1)
        binding.tvEmail.text = email
        binding.tvCode.text = code.toString()
    }
}
