package com.example.referance.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.referance.DB.MyDataBase
import com.example.referance.Adapter.Sotuvchi_Xaridor_Adapter
import com.example.referance.databinding.FragmentSotuvchiXaridorBinding
import com.example.referance.databinding.ItemDialogBinding
import com.example.referance.models.Xaridor

class XaridorFragment : Fragment() {
    lateinit var myDataBase: MyDataBase
    private val binding by lazy { FragmentSotuvchiXaridorBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnAdd.setOnClickListener {
            adding()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        myDataBase = MyDataBase(requireContext())
        binding.rv.adapter = Sotuvchi_Xaridor_Adapter(myDataBase.getXaridor())

    }

    fun adding(){
        val dialog = AlertDialog.Builder(binding.root.context).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialogBinding.root)

        itemDialogBinding.btnSave.setOnClickListener {
            val xaridor = Xaridor(
                0,
                itemDialogBinding.edtName.text.toString(),
                itemDialogBinding.edtPhone.text.toString(),
                itemDialogBinding.edtAdress.text.toString()
            )
            myDataBase = MyDataBase(requireContext())
            myDataBase.addXaridor(xaridor)
            onResume()
            dialog.dismiss()
        }

        dialog.show()
    }



}