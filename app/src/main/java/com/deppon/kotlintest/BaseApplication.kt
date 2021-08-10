package com.deppon.kotlintest

import android.app.Application

/**
 *
 * @author 517987
 * @date 2021/7/13
 */
class BaseApplication :Application() {
    //通过伴生对象 实现类名.方法名调用
    //本质是通过静态内部类实现单例实现 万物皆对象
    //java要使用 BaseApplication.COMPANION.currentApplication()
    //可以使用注解 使用真正的java的static   @JvmStatic
    companion object{
        //编译期常量 会编译成java中  BaseApplication类中的 static final
        const val APP="gaoyj"

        lateinit var currentApplication: Application
            private set

//        private lateinit var currentApplication: Application
//
//        fun currentApplication():Application{
//            return currentApplication
//        }
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication=this
    }
}