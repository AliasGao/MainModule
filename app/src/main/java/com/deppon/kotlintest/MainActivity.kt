package com.deppon.kotlintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    //private var tv:TextView ?=null
    private lateinit var tv:TextView
    private var name:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv=findViewById(R.id.tv)
        //val tv=findViewById<TextView>(R.id.tv)
        //tv?.setOnClickListener(this)
        //tv.setOnClickListener(this)
        tv.setOnClickListener{
            //内部类访问外部类
            this@MainActivity.name="1111"
            //Toast.makeText(this@MainActivity,name,Toast.LENGTH_SHORT).show()
            val user = User("gaoyj", 31)
            if(verify(user)){
                startActivity(Intent(this,DemoActivity::class.java))
            }
        }
    }

    override fun onClick(v: View?) {
        //Toast.makeText(this,(v as TextView).text,Toast.LENGTH_SHORT).show()
//        if(v is TextView){
//            Toast.makeText(this,v.text,Toast.LENGTH_SHORT).show()
//        }
        //跳转需要传入activity的java class 对象
        val user = User("gaoyj", 31)
        if(verify(user)){
            startActivity(Intent(this,DemoActivity::class.java))
        }
        //包级函数很容易混淆
        //dp2px(2.0f)

        //object中定义方法 就可以以类名.方法名来调用了
        //java中要用DpUtils.INSTANCE.dp2px(2.0f)
        //可以查看反编译class查看对应java代码
        //创建了一个DpUtils的单例对象 INSTANCE
        //DpUtils.dp2px(2.0f)

        //使用扩展函数
        2.0f.dp2px()

    }

    private fun verify(user: User?): Boolean {
        if(user?.userName?.length ?:0>0){
        //if(user ==null || TextUtils.isEmpty(user.userName) || user.age!! < 0){
            return true
        }
        return false
    }
}