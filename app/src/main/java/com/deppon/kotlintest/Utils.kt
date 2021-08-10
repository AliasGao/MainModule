//修改java调用时的文件名
//@file:JvmName("KotlinUtils")
package com.deppon.kotlintest

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import kotlin.reflect.KProperty


private val displayMetrics=Resources.getSystem().displayMetrics
//使用扩展函数 对Float进行扩展
fun Float.dp2px()=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, displayMetrics)

//扩展函数是静态解析的 编译期就生成对应代码  类型也就确定了
//所以会根据实际类型 调用不同函数
fun Activity.log(msg:String){
    Log.e("Activity",msg)
}
fun Context.log(msg:String){
    Log.e("Context",msg)
}

//object中定义方法 就可以以类名.方法名来调用了
//java中要用DpUtils.INSTANCE.dp2px(2.0f)
//可以查看反编译class查看对应java代码
//创建了一个DpUtils的单例对象 INSTANCE
//object DpUtils{
//    //包级函数 顶层函数
//    private val displayMetrics=Resources.getSystem().displayMetrics
//
//    fun dp2px(dp:Float)=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, displayMetrics)
//}

object ToastUtils{
    //对于java来说 只会生成一个双参数的函数 不会生成两个重载函数
    //需要加对应注解
    @JvmOverloads
    //靠inline提升性能没有意义  参数是函数类型的才好
    inline fun toast(msg:String?,duration:Int= Toast.LENGTH_SHORT) =Toast.makeText(BaseApplication.currentApplication,msg,duration).show()
}

class View{
    //参数类型是函数类型
    //java中,Kotlin 函数类型就是Functions.kt中的一个个的接口的实现类
    //定义了很多接口  Function1-Function22
    //不使用inline的话 此函数被调用时
    //实际字节码中是会创建出一个个的接口的实现类对象的
    inline fun setOnClickListener(listener:(View)->Boolean){

    }

    //内联函数支持具体化的类型参数
    //使用 reified 修饰符来限定类型参数，
    //现在可以在函数内部访问它了，几乎就像是一个普通的类一样
    inline fun <reified T> test(): Class<T> {
        return T::class.java
    }

    //委托 给lazy  代码只会加载一次 且调用的时候 才执行
    val byTest:TextView by lazy {
        TextView(BaseApplication.currentApplication)
    }
    //如果是val 被委托对象只需要实现get 否则set get 都要
    //委托给对象 去做统一操作
    var token:String by Saver("token")

}

class Saver (var key:String) {
    operator fun getValue(view: View, property: KProperty<*>): String {
        //获取
        return "Token"
    }

    operator fun setValue(view: View, property: KProperty<*>, s: String) {
        //设置
    }


}


fun onClick(view: View):Boolean{
    println("11111")
    return true
}

fun main(){
    val view=View()
    //直接lambda
    view.setOnClickListener {
        println("11111")
        true
    }
    //传递函数引用
    view.setOnClickListener(::onClick)
    //传递一个函数
    view.setOnClickListener(fun (view: View):Boolean {
        println("11111$view")
        return true
    })

    val test = view.test<TextView>()
    println(test.javaClass.name)

    //apply适合对象初始化
    TextView(BaseApplication.currentApplication).apply{
        text="1"
    }
    var textView:TextView?=null
    //let一般配合安全调用符
    val let = textView?.let {
        it.text = "1"
    }

}
