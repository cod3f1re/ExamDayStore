package com.cod3f1re.examdaystore.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.cod3f1re.examdaystore.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setEvents()
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
    private fun setEvents(){
        binding.btnLogin.setOnClickListener {
            if(validation()) {

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
}