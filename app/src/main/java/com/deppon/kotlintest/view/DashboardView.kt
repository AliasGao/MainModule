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
//指针线长度 小于半径
val LINE_LENGTH=120f.dp2px()
//扇形 空白角度
const val OPEN_ANGLE=120f
//刻度数
const val COUNT=26
val DASH_WIDTH=2f.dp2px()
val DASH_HEIGHT=10f.dp2px()
class DashboardView(context: Context,attributeSet: AttributeSet):View(context,attributeSet) {
    private val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    //虚线效果
    private lateinit var pathDashPathEffect:PathDashPathEffect
    //弧线的path  因为需要测量长度  要用到PathMeasure 所以抽取出来
    private val path=Path()
    //代表虚线path
    private val dash=Path()
    init {
        paint.strokeWidth=3f.dp2px()
        paint.style=Paint.Style.STROKE
        dash.addRect(0f,0f, DASH_WIDTH, DASH_HEIGHT,Path.Direction.CCW)

    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //弧线的path 需要计算长度  所以写在onSizeChanged
        path.addArc(width/2f-150f.dp2px(),height/2f-150f.dp2px(),width/2f+150f.dp2px(),height/2f+150f.dp2px(),90+ OPEN_ANGLE/2f,360- OPEN_ANGLE)
        val pathMeasure=PathMeasure(path,false)
        //代表虚线效果 需要设置到paint上 第二个参数是间隔  第三个参数是第一个刻度之前的空余长度
        //(pathMeasure.length- DASH_WIDTH)/26f 减去一个宽度再算间隔 否则没有最后一个刻度
        pathDashPathEffect = PathDashPathEffect(dash, (pathMeasure.length- DASH_WIDTH)/COUNT, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        /** 画弧*/

        //float startAngle 开始角度
        //float sweepAngle 扫过的角度
        //boolean useCenter 使用中心  连接形成扇形

        //抽取到onSizeChanged的path中
        //canvas.drawArc(width/2f-150f.dp2px(),height/2f-150f.dp2px(),width/2f+150f.dp2px(),height/2f+150f.dp2px(),90+ OPEN_ANGLE/2f,360- OPEN_ANGLE,false,paint)
        canvas.drawPath(path,paint)

        /** 画刻度*/
        paint.pathEffect=pathDashPathEffect
        canvas.drawArc(width/2f-RADIUS,height/2f-RADIUS,width/2f+RADIUS,height/2f+RADIUS,90+ OPEN_ANGLE/2f,360- OPEN_ANGLE,false,paint)
        //效果置空
        paint.pathEffect=null
        //根据角度的正弦值 余弦值 计算线的终点坐标
        //假设是第五个刻度
        //90+OPEN_ANGLE/2f+(360-OPEN_ANGLE)/COUNT*5 计算的就是第五个刻度的角度(从→方向顺时针开始计算)
        //sin cos参数都是弧度  所以需要将角度转为弧度 Math.toRadians
        canvas.drawLine(width/2f,height/2f,
            width/2f+ LINE_LENGTH* cos(markToRadians(5)).toFloat(),height/2f+LINE_LENGTH* sin(
                markToRadians(5)
            ).toFloat(),paint)






    }

    /**
     * 根据刻度序号 计算弧度
     */
    private fun markToRadians(index:Int) =
        Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) / COUNT * index).toDouble())


}