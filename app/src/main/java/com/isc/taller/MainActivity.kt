package com.isc.taller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.isc.taller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Se defide el objeto para trabajar la autenticación de firebase...
    private lateinit var auth: FirebaseAuth

    //Se define un objeto para trabajar el enlace con la vista... (xml)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflo el xml para desplegar
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar el ambiente de Firebase y el objeto de autenticación
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btRegistro.setOnClickListener { haceRegistro() }
        binding.btLogin.setOnClickListener { haceLogin() }


    }

    private fun haceLogin() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Autententicando","Autenticado")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.d("Autenticando","Falló")
                    Toast.makeText(baseContext,"Falló autenticando",
                        Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }
    }

    private fun haceRegistro() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Registrando","Registrado")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.d("Registrando","Falló")
                    Toast.makeText(baseContext,"Falló registrando",
                        Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(firebaseUser: FirebaseUser?) {
        if (firebaseUser!=null) {
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        updateUI(user)
    }
}