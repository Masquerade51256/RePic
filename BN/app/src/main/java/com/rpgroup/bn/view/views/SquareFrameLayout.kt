package com.rpgroup.bn.view.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout @JvmOverloads constructor(
        context:Context,
        attrs:AttributeSet?=null,
        defStyleAttrs:Int=0

): FrameLayout(context,attrs,defStyleAttrs){//SquareFrameLayout类继承FrameLayout，并用@JvmOverloads重载了构造函数
override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec)//正方形
}
}
