package com.example.ifmemorygame.DAO

import android.content.ContentValues
import android.content.Context
import com.example.ifmemorygame.model.User

class UserDao {
    private lateinit var banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    public fun insert(u: User) {
        val cv = ContentValues()
        cv.put("nome", u.nome)
        cv.put("time", u.time)
        cv.put("tentativas",u.tentativas)
        this.banco.writableDatabase.insert("user", null, cv)

    }

    fun get(): List<User> {
        val colunas = arrayOf("id", "nome", "time","tentativas")
        val lista = ArrayList<User>()

        val c = this.banco.readableDatabase.query("user", colunas, null, null, null, null, "tentativas")
        c.moveToFirst()
        if (c.count > 0) {
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val nome = c.getString(c.getColumnIndex("nome"))
                val time = c.getString(c.getColumnIndex("time"))
                val tentativas = c.getInt(c.getColumnIndex("tentativas"))
                lista.add(User(id, nome, time, tentativas))
            } while (c.moveToNext())
        }
        return lista
    }

    fun get(index: Int): User? {
        val colunas = arrayOf("id", "nome", "time", "tentativas")
        val where = "id = ?"
        val pwhere = arrayOf(index.toString())
        val c = this.banco.readableDatabase.query("user", colunas, where, pwhere, null, null, null)
        c.moveToFirst()
        if (c.count > 0) {
            val id = c.getInt(c.getColumnIndex("id"))
            val nome = c.getString(c.getColumnIndex("nome"))
            val time = c.getString(c.getColumnIndex("time"))
            val tentativas = c.getInt(c.getColumnIndex("tentativas"))
            return User(id, nome, time, tentativas)
        }
        return null
    }


    fun update(u:User){
        val where = "id = ?"
        val pwhere = arrayOf(u.id.toString())
        val cv = ContentValues()
        cv.put("id", u.id)
        cv.put("nome", u.nome)
        cv.put("time", u.time)
        cv.put("tentativas", u.tentativas)
        this.banco.writableDatabase.update("user",cv, where, pwhere)

    }
    fun delete(id: Int){
        val where = "id = ?"
        val pwhere = arrayOf(id.toString())
        this.banco.writableDatabase.delete("user", where, pwhere)

    }


}