package com.example.project

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.project.data.AppDatabase
import com.example.project.data.Session
import com.example.project.data.SessionDao
import android.widget.Button


const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    lateinit var SessionDao: SessionDao
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "session"
        ).build()
        SessionDao = database.SessionDao()


//        val button: Button = findViewById(R.id.Id)
//        button.setOnClickListener {
//        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        val broadReceiver: BroadcastReceiver = com.example.project.BroadcastReceiver()
        registerReceiver(broadReceiver, intentFilter)



        recyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_users.layoutManager = linearLayoutManager
        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter =MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                recyclerview_users.adapter = myAdapter


            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


}