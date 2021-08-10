package com.deppon.kotlintest

/**
 * Kotlin 测试类
 * @author 517987
 * @date 2021/7/13
 */
//主构造器 没有指定var 创建一个成员变量的话  方法中访问不到
data class User (var userName:String,var age:Int):Any(){

    //var userName:String?=null
//    var age:Int?=null
//        get() {
//            return field?.plus(1)
//        }
//        set(value) {
//            field= value?.plus(1)
//        }
//    //init函数会跟成员变量按代码顺序来初始化
//    // 所以要写到引用到的成员变量后面
//    init{
//        //this.userName=userName
//        this.age=age
//    }


}