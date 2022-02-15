package com.example

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entities.Notas
import com.example.notas.databinding.SeccionNotasBinding
import com.example.repository.NotasRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
class NotasAdapter (private val list: List<Notas>, private val context: Context) :

    RecyclerView.Adapter<NotasAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(val binding: SeccionNotasBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = SeccionNotasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        with(holder.binding) {
            tvTitulo.text = list[position].titulo
            tvDescripcion.text = list[position].descripcion
            btnClean.setOnClickListener {
                val repository = NotasRepository.getRepository(context)
                CoroutineScope(Dispatchers.IO).launch{
                    repository.deleteOneItem(list[position].id)

                }}
        }
    }

    override fun getItemCount(): Int = list.size
}