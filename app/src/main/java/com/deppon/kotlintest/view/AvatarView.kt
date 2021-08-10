package com.deppon.kotlintest.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.deppon.kotlintest.R
import com.deppon.kotlintest.dp2px

/**
 *
 * @author 517987
 * @date 2021/7/21
 */
private val IMAGE_WIDTH=200f.dp2px()
private val IMAGE_PADDING=5f.dp2px()
class AvatarView  (context: Context, attributeSet: AttributeSet): View(context,attributeSet) {
    private val paint= Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(getAvatar(IMAGE_WIDTH.toInt()), IMAGE_PADDING, IMAGE_PADDING,paint)

    }

    private fun getAvatar(width:Int): Bitmap{
        val options=BitmapFactory.Options()
        options.inJustDecodeBounds=true
        BitmapFactory.decodeResource(resources,R.mipmap.avatar,options)
        options.inJustDecodeBounds=false
        //原图片对应的像素实际密度
        options.inDensity=options.outWidth
        //此view的目标密度
        //BitmapFactory会根据上面两个参数计算出对应的缩放比去加载图片
        options.inTargetDensity=width
        return BitmapFactory.decodeResource(resources,R.mipmap.avatar,options)
    }



}