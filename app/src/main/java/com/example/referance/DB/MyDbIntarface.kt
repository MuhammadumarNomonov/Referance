package com.example.referance.DB

import com.example.referance.models.Buyurtma
import com.example.referance.models.Sotuvchi
import com.example.referance.models.Xaridor

interface MyDbIntarface {



    fun addSotuvchi(sotuvchi: Sotuvchi)
    fun getSotuvchi():List<Sotuvchi>


    fun addXaridor(xaridor: Xaridor)
    fun getXaridor():List<Xaridor>


    fun addBuyurtma(buyurtma: Buyurtma)
    fun getBuyurtma():List<Buyurtma>


    fun getSotuvchiBYID(id:Int):Sotuvchi
    fun getXaridorByID(id: Int):Xaridor

}