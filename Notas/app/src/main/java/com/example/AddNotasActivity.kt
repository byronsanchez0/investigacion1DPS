package com.example

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import com.example.entities.Notas
import com.example.notas.databinding.ActivityAddNotasBinding
import com.example.repository.NotasRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNotasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }

    private fun addListener() {
        val repository = NotasRepository.getRepository(this)
        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etTitle.text.isBlank() || etDescription.text.isBlank()) {
                    Snackbar.make(this.root, "Rellene todos los campos", Snackbar.LENGTH_SHORT).show()
                } else {

                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                Notas(
                                    titulo = etTitle.text.toString(),
                                    descripcion = etDescription.text.toString(),
                                )
                            )
                        }
                        onBackPressed()
                    }
                }
            }
        }
    }

private fun hideKeyboard() {
    val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
}
}

