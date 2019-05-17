package com.example.ifmemorygame.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context?):
    SQLiteOpenHelper(context, "banco",null, 1 ){


    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table user(" +
                "id integer primary key autoincrement," +
                " nome text, time text,  tentativas integer)"
        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}