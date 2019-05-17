package com.example.ifmemorygame

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ifmemorygame.DAO.UserDao
import com.example.ifmemorygame.model.User
import kotlinx.android.synthetic.main.rowranking.view.*

class MainRankingActivity : AppCompatActivity() {
    private lateinit var nameUserTv : TextView
    private lateinit var scoreTv : TextView
    private lateinit var userDao : UserDao
    private lateinit var lvRanking : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ranking)
        this.lvRanking = findViewById(R.id.lvRanking)
        supportActionBar!!.title = "Ranking"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userDao = UserDao(this)
        Log.i("APP_OPA","dao = "+userDao.get().toString())
        var myUsers : MyUsersAdapter = MyUsersAdapter(this, userDao.get() as ArrayList<User>)
        lvRanking.adapter = myUsers



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    inner class MyUsersAdapter : BaseAdapter {
        var listUsers = ArrayList<User>()
        var context: Context?=null

        constructor(context: Context, listContactsAdapter: ArrayList<User>): super(){
            this.listUsers = listContactsAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.rowranking, null)
            var myUser = listUsers[position]
            myView.nameUserTv.text = myUser.nome
            myView.scoreTv.text = myUser.tentativas.toString()
            val lp = LinearLayout.LayoutParams(
                70,
                70
            )
            lp.setMargins(20,30,0,0)
            myView.imgTrofeu.setLayoutParams(lp)
            //myView.imgTrofeu.setPadding(0, 30, 0, 0)
            if(position ==0){
                myView.imgTrofeu.setImageResource(R.drawable.gold)
            }else if(position ==1){
                myView.imgTrofeu.setImageResource(R.drawable.silver)
            }else if(position ==2){
                myView.imgTrofeu.setImageResource(R.drawable.bronze)
            }else {
                myView.imgTrofeu.setImageResource(R.drawable.trans)
            }

            Log.i("APP_OPA",myUser.toString())
            return myView;

        }

        override fun getItem(position: Int): Any {
            return listUsers[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listUsers.size
        }

    }
}
