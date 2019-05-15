package com.example.ifmemorygame

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.example.ifmemorygame.model.TeacherList
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Main2Activity : AppCompatActivity() {
    private lateinit var curView: ImageView
    private lateinit var gridView : GridView
    private lateinit var dadosfotos : MutableList<String>
    private lateinit var view_timer : Chronometer
    private lateinit var txt_tent : TextView
    private lateinit var myDialog : Dialog
    private lateinit var txt_timer : TextView
    private lateinit var txt_attempts : TextView
    private lateinit var btnOkDialog: Button
    private lateinit var btnCancelDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        view_timer = findViewById(R.id.view_timer)
        txt_tent = findViewById(R.id.txt_tent)
        // view_timer.setCountDown(true)
        view_timer.base = SystemClock.elapsedRealtime()
        view_timer.start()

        var countPair =0//quantidade acertos
        var countTentatives = 0 // quantidade tentativas
        var jogadaAtual = 0;
        var pos =  arrayOf(0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7).toList().shuffled()
        dadosfotos = arrayListOf()

        var page = 1
        do{
            TeacherService().list(page).enqueue(object : Callback<TeacherList> {
                override fun onResponse(call: Call<TeacherList>, response: Response<TeacherList>) {
                    val listteachers = response.body()
                    listteachers?.let {
                        showTeachers(it)
                    }

                }

                override fun onFailure(call: Call<TeacherList>, t: Throwable) {
                    Log.e("onFailure error", t?.message)
                }

            })
            page++

        }while (page < 5)
        dadosfotos.shuffled()
        for (dado in dadosfotos){
            Log.i("APP_TESTADOR","dado"+dado)
        }
        

        var currentPos = -1
        this.gridView = findViewById(R.id.gridView)
        var imgAdapter = ImageAdapter(this)
        gridView.adapter = (imgAdapter)
        gridView.setOnItemClickListener { parent, view, position, id ->
            view as ImageView
            Picasso.with(this).load(dadosfotos[pos[position]])
                .transform(RoundedCornersTransformation(10, 10)).into(view)
            val handler = Handler()
            if(currentPos < 0){
                currentPos = position
                curView = view as ImageView
            }else {
                if(currentPos == position){
                    jogadaAtual = 1

                }else if(pos[currentPos]!=pos[position]){
                    view as ImageView
                    curView.setImageResource(R.drawable.hidden)
                    handler.postDelayed({
                        Picasso.with(this).load(R.drawable.hidden).into(view)
                    }, 1000)
                    jogadaAtual = 2
                }else {
                    view as ImageView
                    Picasso.with(this).load(dadosfotos[pos[position]])
                        .transform(RoundedCornersTransformation(10, 10)).into(view)
                    countPair++;
                    view.setClickable(true)
                    curView.setClickable(true)
                    Log.i("APP_TESTADOR","foii")
                    jogadaAtual = 2
                    if(countPair == 8){
                        view_timer.stop()
                        myDialog = Dialog(this)
                        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        myDialog.setContentView(R.layout.dialog_activity)
                        myDialog.setTitle("My first dialog box")
                        txt_attempts = myDialog.findViewById(R.id.txt_attempts)
                        txt_timer = myDialog.findViewById(R.id.txt_timer)
                        txt_timer.setText(view_timer.text.toString())
                        var resultTents = txt_tent.text.toString().toInt() +1
                        txt_attempts.setText(resultTents.toString())

                        this.btnOkDialog = myDialog.findViewById(R.id.btnOk)
                        this.btnCancelDialog = myDialog.findViewById(R.id.btnCancel)
                        this.btnOkDialog.setOnClickListener({
                            Toast.makeText(this,"you confirmed", Toast.LENGTH_LONG).show()
                            myDialog.cancel()
                        })
                        this.btnCancelDialog.setOnClickListener({
                            Toast.makeText(this,"you canceled", Toast.LENGTH_LONG).show()
                            myDialog.cancel()
                        })
                        myDialog.show()

                    }
                }
                if(jogadaAtual!=1){
                    countTentatives++
                    txt_tent.setText(countTentatives.toString())
                    currentPos = -1
                }
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun showTeachers(teacherList: TeacherList){


        for (teacher in teacherList.teachers){
            Log.i("APP_TEACHER","url = "+teacher.url)
            Log.i("APP_TEACHER", "nome = "+teacher.nome)
            Log.i("APP_TEACHER","frase marcante ="+teacher.frasemarcante)
            Log.i("APP_TEACHER","foto = "+teacher.foto)
            this.dadosfotos.add(teacher.foto)
        }

    }


    inner class ImageAdapter : BaseAdapter {

        var context: Context?=null

        constructor(context: Context){
            this.context = context
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getItem(position: Int): Any {
            return 0

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var imgView: ImageView
            if (convertView == null){
                imgView = ImageView(context)
                //imgView.setLayoutParams(RelativeLayout.LayoutParams(320, 320))
                val lp = LinearLayout.LayoutParams(
                    260,
                    330
                )
                lp.setMargins(100, 100, 100, 100)
                imgView.setLayoutParams(lp)
                imgView.setPadding(10, 10, 0, 10)
                imgView.setScaleType(ImageView.ScaleType.FIT_XY)
                imgView.setImageResource(R.drawable.hidden)

            }else{
                imgView =  convertView as ImageView
                imgView.setImageResource(R.drawable.hidden)

            }
            return imgView

        }
        override fun getCount(): Int {
            return 16;

        }

    }


}

