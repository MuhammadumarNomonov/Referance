package com.example.referance.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.referance.models.Buyurtma
import com.example.referance.models.Sotuvchi
import com.example.referance.models.Xaridor

class MyDataBase(context: Context) : SQLiteOpenHelper(context,"Mahsulot_db", null,1), MyDbIntarface {

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table Sotuchi_table (id INTEGER not null primary key autoincrement unique, name text not null, number text not null)"
        p0?.execSQL(query)
        val query2 = "create table Xaridor_table (id INTEGER not null primary key autoincrement unique, name text not null, number text not null, adress text not null)"
        p0?.execSQL(query2)
        val query3 = "create table buyurtma (id integer not null primary key autoincrement unique, sotuchi_id integer not null, xaridor_id integer not null, name text not null, foreign key (sotuchi_id) REFERENCES Sotuchi_table(id), foreign key (xaridor_id) REFERENCES Xaridor_table(id))"
        p0?.execSQL(query3)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun addSotuvchi(sotuvchi: Sotuvchi) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", sotuvchi.name)
        contentValues.put("number", sotuvchi.number)
        database.insert("Sotuchi_table", null, contentValues)
        database.close()
    }

    override fun getSotuvchi(): List<Sotuvchi> {
        val list = mutableListOf<Sotuvchi>()
        val database = this.readableDatabase
        val query = "select * from Sotuchi_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val sotuvchi = Sotuvchi(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(sotuvchi)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", xaridor.name)
        contentValues.put("number", xaridor.number)
        contentValues.put("adress", xaridor.adress)
        database.insert("Xaridor_table", null, contentValues)
        database.close()
    }

    override fun getXaridor(): List<Xaridor> {
        val list = mutableListOf<Xaridor>()
        val database = this.readableDatabase
        val query = "select * from Xaridor_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val xaridor = Xaridor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                list.add(xaridor)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("sotuchi_id", buyurtma.sotuvchi.id)
        contentValues.put("xaridor_id", buyurtma.xaridor.id)
        contentValues.put("name", buyurtma.nomi)
        database.insert("buyurtma", null, contentValues)
        database.close()
    }

    override fun getBuyurtma(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = this.readableDatabase
        val query = "select * from buyurtma"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val buyurtma = Buyurtma(
                    cursor.getInt(0),
                    getSotuvchiBYID(cursor.getInt(1)),
                    getXaridorByID(cursor.getInt(2)),
                    cursor.getString(3)
                )
                list.add(buyurtma)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun getSotuvchiBYID(id: Int): Sotuvchi {
        val database = this.readableDatabase
        val cursor = database.query(
            "Sotuchi_table",
            arrayOf("id", "name", "number"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        if (!cursor.moveToFirst()) {
            cursor.close()
            throw IllegalArgumentException("Sotuvchi ID topilmadi: $id")
        }

        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        cursor.close()
        return sotuvchi
    }

    override fun getXaridorByID(id: Int): Xaridor {
        val database = this.readableDatabase
        val cursor = database.query(
            "Xaridor_table",
            arrayOf("id", "name", "number", "adress"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        if (!cursor.moveToFirst()) {
            cursor.close()
            throw IllegalArgumentException("Xaridor ID topilmadi: $id")
        }

        val xaridor = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        cursor.close()
        return xaridor
    }
}
