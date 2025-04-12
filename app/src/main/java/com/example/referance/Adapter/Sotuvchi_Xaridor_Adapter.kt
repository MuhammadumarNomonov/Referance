package com.example.referance.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referance.databinding.ItemSotuvchiXaridorBinding
import com.example.referance.models.Sotuvchi
import com.example.referance.models.Xaridor

class Sotuvchi_Xaridor_Adapter<T>(
    private val list: List<T>, ) : RecyclerView.Adapter<Sotuvchi_Xaridor_Adapter<T>.SotuvchiXaridorVh>() {



    inner class SotuvchiXaridorVh(val binding: ItemSotuvchiXaridorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindSotuvchi(sotuvchi: Sotuvchi) {
            binding.tvName.text = sotuvchi.name
            binding.tvPhone.text = sotuvchi.number
            binding.tvAdress.visibility = View.GONE
        }

        fun onBindXaridor(xaridor: Xaridor) {
            binding.tvName.text = xaridor.name
            binding.tvPhone.text = xaridor.number
            binding.tvAdress.text = xaridor.adress



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SotuvchiXaridorVh {
        val binding = ItemSotuvchiXaridorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SotuvchiXaridorVh(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SotuvchiXaridorVh, position: Int) {
        val item = list[position]
        when (item) {
            is Sotuvchi -> holder.onBindSotuvchi(item)
            is Xaridor -> holder.onBindXaridor(item)
        }
    }


}
