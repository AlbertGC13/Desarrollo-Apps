package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SegundaPantalla : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_pantalla)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val genero = intent.getStringExtra("genero")
        val fecha = intent.getStringExtra("fecha")
        val leGustaProgramar = intent.getStringExtra("leGustaProgramar")
        val lenguajesFavoritos = intent.getStringArrayListExtra("lenguajesFavoritos")

        val txtNomApell = findViewById<TextView>(R.id.txtNomApell)
        txtNomApell.text = "Hola! Mi nombre es: $nombre $apellido"

        val txtGenFecha = findViewById<TextView>(R.id.txtGenFecha)
        txtGenFecha.text = "Soy $genero y nací en la fecha $fecha"

        val txt3 = findViewById<TextView>(R.id.txt3)
        if (lenguajesFavoritos != null) {
            txt3.text = "Le gusta programar? $leGustaProgramar. Mis lenguajes favoritos son: ${lenguajesFavoritos.joinToString(", ")}"
        }
    }
}
