package com.example.referance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.referance.Adapter.BuyurtmaAdapter
import com.example.referance.DB.MyDataBase
import com.example.referance.R
import com.example.referance.databinding.FragmentBuyurtmaBinding
import com.example.referance.models.Buyurtma
import com.example.referance.models.Sotuvchi
import com.example.referance.models.Xaridor

class BuyurtmaFragment : Fragment() {
    lateinit var myDbhelper: MyDataBase
   private val binding by lazy { FragmentBuyurtmaBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDbhelper = MyDataBase(requireContext())

        loadSpinner()

        binding.btnSave1.setOnClickListener {
            val buyurtma = Buyurtma(
                0,
                sotuvchi1List[binding.spinnerSotuchi.selectedItemPosition],
                xaridor1List[binding.spinnerXaridor.selectedItemPosition],
                binding.edtName.text.toString()
            )
            myDbhelper.addBuyurtma(buyurtma)
            Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show()
        }


        return binding.root

    }
    lateinit var  sotuvchi1List:ArrayList<Sotuvchi>
    lateinit var  xaridor1List:ArrayList<Xaridor>

    private fun loadSpinner() {
        sotuvchi1List = ArrayList()
        xaridor1List = ArrayList()


        sotuvchi1List.addAll(myDbhelper.getSotuvchi())
        xaridor1List.addAll(myDbhelper.getXaridor())


        val snl = ArrayList<String>()
        val xnl = ArrayList<String>()


        sotuvchi1List.forEach{ snl.add(it.name)}
        xaridor1List.forEach { xnl.add(it.name) }

        binding.spinnerSotuchi.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, snl)
        binding.spinnerXaridor.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, xnl)



    }

    override fun onResume() {
        super.onResume()
        binding.rv1.adapter = BuyurtmaAdapter(myDbhelper.getBuyurtma())

    }


}