package com.example.gestor_gastos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gestor_gastos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonAtualizarDados.setOnClickListener {
            val editLocal : String = binding.editNome.text.toString()
            val editPreco : String = binding.editPreco.text.toString()

            if (editLocal.isNotEmpty() && editPreco.isNotEmpty()) {
                enviarDados(editLocal, editPreco.toDouble())
            } else {
                Toast.makeText(applicationContext, "Preencha os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enviarDados(nome: String, valor: Double) {
        val url="https://script.google.com/macros/s/AKfycbzIQOXfYoBvVQRgoO5Ds9ZN4X38RPTSTZ6V71ZovcCeSC9v0zm4eJsKZM5hybKpFMJBcg/exec"
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["nome"] = nome
                params["valor"] = valor.toString()

                return params
            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(stringRequest)
    }
}