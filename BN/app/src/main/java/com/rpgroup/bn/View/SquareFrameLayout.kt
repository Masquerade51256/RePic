package com.rpgroup.bn.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet?=null,
        defStyleAttrs:Int=0

): FrameLayout(context,attrs,defStyleAttrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}