package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notas.R
import com.example.notas.databinding.ActivityMainBinding
import com.example.repository.NotasRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class  MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
    }
    private fun buildList() {

        val repository = NotasRepository.getRepository(this)

        val layoutManager = GridLayoutManager(this, 1)

        lifecycleScope.launch {
            repository.allNotas.collect { notas ->
                binding.rvRvnotas.apply {
                    adapter = NotasAdapter(notas, this@MainActivity)
                    setLayoutManager(layoutManager)
                }
            }
        }
    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddNotasActivity::class.java))
        }



    }


}