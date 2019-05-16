package com.example.ifmemorygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.content.Intent



class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay : Button
    private lateinit var btnList : Button
    private lateinit var btnRanking: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnPlay = findViewById(R.id.btnPlay)
        this.btnList = findViewById(R.id.btnList)
        this.btnRanking = findViewById(R.id.btnRanking)

        this.btnPlay.setOnClickListener({ play(it) })
        this.btnList.setOnClickListener({ list(it) })
        this.btnRanking.setOnClickListener({ ranking(it) })

    }

    fun play(view : View){
        var intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)


    }
    fun list (view : View){
        var intent = Intent(this, MainListActivity::class.java)
        startActivity(intent)

    }

    fun ranking (view: View) {

    }




}
