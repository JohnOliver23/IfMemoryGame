package com.example.ifmemorygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.content.Intent



class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay : ImageButton
    private lateinit var btnList : ImageButton
    private lateinit var btnRanking: ImageButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        this.btnPlay = findViewById(R.id.btnPlay)
        this.btnList = findViewById(R.id.btnTeachers)
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
        var intent = Intent(this, MainRankingActivity::class.java)
        startActivity(intent)

    }




}
