package com.cod3f1re.examdaystore.view.login

import android.R.drawable
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cod3f1re.examdaystore.R
import com.cod3f1re.examdaystore.databinding.ActivityLoginBinding
import com.cod3f1re.examdaystore.model.apientities.login.LoginDataResponse
import com.cod3f1re.examdaystore.model.apientities.login.LoginRequest
import com.cod3f1re.examdaystore.model.repository.login.LoginRepository
import com.cod3f1re.examdaystore.utils.APIModule
import com.cod3f1re.examdaystore.view.MainActivity
import com.cod3f1re.examdaystore.view.location.LocationActivity
import com.cod3f1re.examdaystore.viewmodel.login.LoginViewModel
import com.cod3f1re.examdaystore.viewmodel.login.LoginViewModelFactory


class LoginActivity : AppCompatActivity() {

    //Contiene la vista inflada para acceder a los ID's
    private lateinit var binding: ActivityLoginBinding

    //Inicializar el ViewModel
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(LoginRepository(APIModule.getAPIService()))
    }
    private companion object{
        //Canal para las notificaciones
        private const val CHANNEL_ID = "channelLogin"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setObservers()
        setEvents()
    }

    private fun setEvents(){
        binding.btnLogin.setOnClickListener {
            if(validation()) {
                //Se consulta en el ws la configuracion del usuario
                viewModel.getLoginGetCode(LoginRequest(binding.etEmail.text.toString(),binding.etPassword.text.toString(),"Abraham"))
            }
        }
        binding.btnLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cleanErrors()
            }
        })
    }
    private fun validation(): Boolean{

        cleanErrors()

        var validation = false

        if(binding.etEmail.text.toString().isEmpty() && binding.etPassword.text.toString().isEmpty()){
            binding.ilEmail.error = "Introduce un correo electrónico y contraseña valida."
            binding.ilPassword.error = "Introduce un correo electrónico y contraseña valida."
        }else{
            if(binding.etEmail.text.toString().isEmpty()){
                binding.ilEmail.error = ""
                binding.ilPassword.error = "Introduce un correo electrónico valido."
            }else{
                if(binding.etPassword.text.toString().isEmpty()){
                    binding.ilPassword.error = "Introduce una contraseña valida."
                    binding.etPassword.setText("")
                    binding.etPassword.requestFocus()
                }else{
                    validation = true
                }
            }
        }
        return validation
    }

    private fun cleanErrors(){
        binding.ilEmail.error = null
        binding.ilPassword.error = null
    }

    /**
     * Este metodo inicializa los observadores, para dispararse en cuanto haya algun cambio
     * en los liveData
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setObservers(){
        viewModel.loginLiveData.observe(this){ login->
            //Se muestra el codigo obtenido
            Log.e("Resultado del codigo", login.code)

            createNotificationChannel(login)

            //Escuchar el tap, para mandar a la activity de localizacion
            val mainIntent = Intent(this,LocationActivity::class.java)
            mainIntent.putExtra("email",binding.etEmail.text.toString())
            mainIntent.putExtra("code",login.code.toInt())
            mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val mainPendingIntent = PendingIntent.getActivity(this,1,mainIntent,PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

            //Se le da un id unico a la notificacion (Aprovechamos el id que genera el endpoint)
            val notificationId = login.code.toInt()

            //Establecemos la notificacion y sus parametros
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this,"$CHANNEL_ID")
            notificationBuilder.setSmallIcon(R.drawable.ic_notification)
            notificationBuilder.setContentTitle("Ha iniciado sesion correctamente")
            notificationBuilder.setContentText("El codigo de inicio de sesion, es: ${login.code}, presiona si quieres enviar tu ubicacion")
            notificationBuilder.setAutoCancel(true)
            notificationBuilder.setContentIntent(mainPendingIntent)
            notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
            val notificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManagerCompat.notify(notificationId,notificationBuilder.build())
        }
    }

    private fun createNotificationChannel(login: LoginDataResponse){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "Ha iniciado sesion correctamente"
            val description = "El codigo de inicio de sesion, es: ${login.code}, presiona si quieres enviar tu ubicacion"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID,name,importance)
            notificationChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}