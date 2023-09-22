package com.example.myfirstapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.etextNombre)
        val editText2 = findViewById<EditText>(R.id.etextApellido)
        val textView = findViewById<EditText>(R.id.etextFecha)
        val botonEnviar = findViewById<Button>(R.id.button2)
        val botonLimpiar = findViewById<Button>(R.id.button)
        val spinnerG = findViewById<Spinner>(R.id.spnGenero)
        val adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerG.adapter = adapter
        val radioButtonSi = findViewById<RadioButton>(R.id.radioButton)
        val radioButtonNo = findViewById<RadioButton>(R.id.radioButton2)
        val cbJava = findViewById<CheckBox>(R.id.cbJava)
        cbJava.isEnabled = false
        val cbPython = findViewById<CheckBox>(R.id.cbPython)
        cbPython.isEnabled = false
        val cbJS = findViewById<CheckBox>(R.id.cbJS)
        cbJS.isEnabled = false
        val cbC = findViewById<CheckBox>(R.id.cbC)
        cbC.isEnabled = false
        val cbCSharp = findViewById<CheckBox>(R.id.cbCSharp)
        cbCSharp.isEnabled = false
        val cbGo = findViewById<CheckBox>(R.id.cbGo)
        cbGo.isEnabled = false

        textView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedDate = "${selectedDayOfMonth}/${selectedMonth + 1}/$selectedYear"
                    textView.setText(selectedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        radioButtonSi.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cbJava.isEnabled = true
                cbPython.isEnabled = true
                cbJS.isEnabled = true
                cbC.isEnabled = true
                cbCSharp.isEnabled = true
                cbGo.isEnabled = true
            }
        }

        radioButtonNo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cbJava.isEnabled = false
                cbPython.isEnabled = false
                cbJS.isEnabled = false
                cbC.isEnabled = false
                cbCSharp.isEnabled = false
                cbGo.isEnabled = false

                cbJava.isChecked = false
                cbPython.isChecked = false
                cbJS.isChecked = false
                cbC.isChecked = false
                cbCSharp.isChecked = false
                cbGo.isChecked = false
            }
        }

        botonEnviar.setOnClickListener {
            val nombre = editText.text.toString()
            val apellido = editText2.text.toString()
            val genero = spinnerG.selectedItem.toString()
            val fecha = textView.text.toString()
            val leGustaProgramar = if (radioButtonSi.isChecked) "Sí" else "No"
            val lenguajesFavoritos = mutableListOf<String>()
            if (cbJava.isChecked) lenguajesFavoritos.add("Java")
            if (cbPython.isChecked) lenguajesFavoritos.add("Python")
            if (cbJS.isChecked) lenguajesFavoritos.add("JavaScript")
            if (cbC.isChecked) lenguajesFavoritos.add("C")
            if (cbCSharp.isChecked) lenguajesFavoritos.add("C#")
            if (cbGo.isChecked) lenguajesFavoritos.add("Go")
            // Validaciones
            if (nombre.isEmpty() || apellido.isEmpty() || genero.isEmpty() || fecha == "1/1/2000") {
                Toast.makeText(this, "Por favor completa todos los campos, son obligatorios.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (leGustaProgramar == "Sí" && listOf(cbJava, cbPython, cbJS, cbC, cbCSharp, cbGo).none { it.isChecked }) {
                Toast.makeText(this, "Selecciona al menos un lenguaje de programación.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val intent = Intent(this, SegundaPantalla::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            intent.putExtra("genero", genero)
            intent.putExtra("fecha", fecha)
            intent.putExtra("leGustaProgramar", leGustaProgramar)
            intent.putStringArrayListExtra("lenguajesFavoritos", ArrayList(lenguajesFavoritos))
            startActivity(intent)
        }

        botonLimpiar.setOnClickListener {
            editText.text = null
            editText2.text = null
            textView.text = null
            spinnerG.setSelection(0)
            radioButtonSi.isChecked = true
            radioButtonNo.isChecked = false
            cbJava.isChecked = false
            cbPython.isChecked = false
            cbJS.isChecked = false
            cbC.isChecked = false
            cbCSharp.isChecked = false
            cbGo.isChecked = false

        }
    }
}