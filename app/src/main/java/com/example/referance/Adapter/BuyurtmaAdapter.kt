package com.example.referance.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referance.databinding.ItemSotuvchiXaridorBinding
import com.example.referance.models.Buyurtma

class BuyurtmaAdapter(var list: List<Buyurtma>):RecyclerView.Adapter<BuyurtmaAdapter.VH>() {
    inner class VH(val itemRvBinding: ItemSotuvchiXaridorBinding):RecyclerView.ViewHolder(itemRvBinding.root){

        fun OnBind(buyurtma: Buyurtma){
            itemRvBinding.tvName.text = buyurtma.nomi
            itemRvBinding.tvPhone.text = buyurtma.sotuvchi.name
            itemRvBinding.tvAdress.text = buyurtma.xaridor.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemSotuvchiXaridorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
    holder.OnBind(list[position])
    }
}