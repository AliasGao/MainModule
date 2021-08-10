package com.deppon.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        val app = BaseApplication.APP
        val currentApplication = BaseApplication.currentApplication
        val mutableListOf = mutableListOf<String>("1", "2", "3", "4")
        for (s in mutableListOf){
            val toString = s.toString()
        }
        val filter = mutableListOf.filter {
            it != "2"
        }
        //Toast.makeText(this@DemoActivity,filter.toString(),Toast.LENGTH_SHORT).show()
        //ToastUtils.toast(filter.toString())


        val url="https://api.github.com/users/rengwuxian/repos"
        //val okHttpClient = OkHttpClient()
        val okHttpClient = OkHttpClient.Builder()
            .authenticator { route, response ->
                //验证失败时 刷新token
                response.request.newBuilder()
                    .header("Authorization", "Bearer *******")
                    .build()
            }.build()

        val request:Request=Request.Builder().url(url).build()
        okHttpClient.newCall(request)
            .enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

            }

        })



    }
}