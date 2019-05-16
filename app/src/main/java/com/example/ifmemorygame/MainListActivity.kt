package com.example.ifmemorygame

import android.content.Context
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import com.example.ifmemorygame.model.Teacher
import com.example.ifmemorygame.model.TeacherList
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.row.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListActivity : AppCompatActivity() {
    private lateinit var teachersLv: ListView
    private lateinit var teacherListAux: ArrayList<Teacher>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        teacherListAux = ArrayList<Teacher>()
        this.teachersLv = findViewById(R.id.lvTeachers)

        supportActionBar!!.title = "List Teachers"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)



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
            if(page==5){
                    teachersLv.adapter = TeachersAdapter(this,teacherListAux )
                }

        }while (page < 5)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    inner class TeachersAdapter : BaseAdapter {
        var listTeachers = ArrayList<Teacher>()
        var context: Context?=null
        constructor(context: Context, listContactsAdapter: ArrayList<Teacher>): super(){
            this.listTeachers = listContactsAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.row, null)
            var myTeacher = listTeachers[position]
            myView.nameTv.text = myTeacher.nome
            myView.descriptionTv.text = myTeacher.frasemarcante
            Picasso.with(this.context).load(myTeacher.foto).resize(170,170).transform(CropCircleTransformation()).into(myView.imgFoto)
            return myView
        }

        override fun getItem(position: Int): Any {
            return listTeachers[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listTeachers.size

        }
    }

    private fun showTeachers(teacherList: TeacherList){


        for (teacher in teacherList.teachers){
            Log.i("APP_TEACHER","url = "+teacher.url)
            Log.i("APP_TEACHER", "nome = "+teacher.nome)
            Log.i("APP_TEACHER","frase marcante ="+teacher.frasemarcante)
            Log.i("APP_TEACHER","foto = "+teacher.foto)
            teacherListAux.add(teacher)

        }

            teachersLv.adapter=  TeachersAdapter(this,teacherListAux )




    }
}
