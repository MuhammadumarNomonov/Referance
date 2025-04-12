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
import com.example.referance.models.Sotuvchi


class SotuvchiFragment : Fragment() {
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
        myDataBase= MyDataBase(requireContext())
        binding.rv.adapter= Sotuvchi_Xaridor_Adapter(myDataBase.getSotuvchi(),
            )
    }

    fun adding() {
        val dialog = AlertDialog.Builder(binding.root.context).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
        itemDialogBinding.edtAdress.visibility = View.GONE

        itemDialogBinding.btnSave.setOnClickListener {
            val sotuvchi = Sotuvchi(
                0,
                itemDialogBinding.edtName.text.toString(),
                itemDialogBinding.edtPhone.text.toString(),


            )
            myDataBase = MyDataBase(requireContext())
            myDataBase.addSotuvchi(sotuvchi)
            dialog.dismiss()
            onResume()
        }

        dialog.setView(itemDialogBinding.root)

        dialog.show()
    }
}
