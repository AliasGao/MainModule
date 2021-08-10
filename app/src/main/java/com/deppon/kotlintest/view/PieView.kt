package com.deppon.kotlintest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.deppon.kotlintest.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 * @author 517987
 * @date 2021/7/20
 */
private val RADIUS=150f.dp2px()
//x轴方向上的偏移量
const val X_OFFSET=10f
//半径上的偏移量
const val RADIUS_OFFSET=20f
private val ANGLES= floatArrayOf(60f,90f,150f,60f)
private val COLORS= listOf(Color.parseColor("#C2185B"),Color.parseColor("#00ACC1"),Color.parseColor("#558B2f"),Color.parseColor("#5D4037"))
class PieView(context: Context,attributeSet: AttributeSet):View(context,attributeSet) {
    private val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {
        /**画饼图 */
        //遍历画饼图
        var startAngle=0f
        for ((index,angle) in ANGLES.withIndex()){
            //设置color
            paint.color=COLORS[index]
            if(index==0){
                canvas.save()
                //根据x轴上的偏移量X_OFFSET  计算y的偏移量(tan)
                //canvas.translate(OFFSET, OFFSET* tan(Math.toRadians((startAngle+angle/2f).toDouble())).toFloat())
                //根据半径上的偏移量RADIUS_OFFSET 初始角度 所在扇形角度 计算x的偏移量cos 和y的偏移量sin
                canvas.translate(RADIUS_OFFSET* cos(Math.toRadians(startAngle+angle/2f.toDouble())).toFloat(), RADIUS_OFFSET* sin(Math.toRadians(startAngle+angle/2f.toDouble())).toFloat())
            }
            //扇形 需要连到圆心
            canvas.drawArc(width/2f-RADIUS,height/2f-RADIUS,width/2f+RADIUS,height/2f+RADIUS,startAngle,angle,true,paint)
            //计算下一个扇形初始角度
            startAngle+=angle

            if(index==0){
                canvas.restore()
            }
        }


    }



}