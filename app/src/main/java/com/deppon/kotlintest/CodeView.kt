package com.deppon.kotlintest

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.util.jar.Attributes

/**
 *
 * @author 517987
 * @date 2021/7/13
 */
//主构造器 没有指定var 创建一个成员变量的话  方法中访问不到
class CodeView(context: Context,attributeSet: AttributeSet?): AppCompatTextView (context,attributeSet){
    constructor(context: Context):this(context,null)
    init{
        val arrayOf = arrayOf("1", "2")
        val intArrayOf = intArrayOf(1, 2, 3)
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
    }

}