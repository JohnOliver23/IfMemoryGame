package com.example.ifmemorygame.model

class User {
    var id: Int
    var nome: String
    var time: String
    var tentativas: Int

    constructor(id: Int, nome: String, time: String, tentativas: Int) {
        this.id = id
        this.nome = nome
        this.time = time
        this.tentativas = tentativas
    }

    constructor(nome: String, time: String, tentativas: Int) {
        this.id = -1
        this.nome = nome
        this.time = time
        this.tentativas = tentativas
    }

    override fun toString(): String {
        return "${nome} - ${time} - ${tentativas}"
    }
}



