package com.github.lhoyong.transparencyseekbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * Created by ihoyong on 2018. 4. 4..
 */
class TransParencySeekBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ProgressListener {

    override fun invoke(progress: Int) {
        this.progress = progress

        invalidate()
    }

    private val a = attrs?.let { context.obtainStyledAttributes(attrs, R.styleable.TransParencySeekBar, defStyleAttr, defStyleAttr) }

    var listener: (ProgressListener)? = null

    var progressBackgroundColor: Int = a!!.getColor(R.styleable.TransParencySeekBar_backgroundColor, Color.BLUE)
    var progressColor: Int = a!!.getColor(R.styleable.TransParencySeekBar_progressColor, Color.WHITE)
    var maxProgress: Int = a!!.getInteger(R.styleable.TransParencySeekBar_maxProgress, 100)
    var progress: Int = a!!.getInteger(R.styleable.TransParencySeekBar_progress, 0)

    var isPress: Boolean = true
    var thumbX: Float = 0F

    private val thumb: Drawable = a?.getDrawable(R.styleable.TransParencySeekBar_thumb)
            ?: resources.getDrawable(R.drawable.thumb)

    init {
        a?.recycle()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isPress) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e("actiondown", "${event.x} ${event.y}")
                }
                MotionEvent.ACTION_MOVE -> thumbPosition(event.x)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {

                }
            }
        }

        return isPress
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)

        setMeasuredDimension(width, height)

    }

    // 이미지 그리는곳
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        backgroundDraw(canvas)
        progressDraw(canvas)

    }

    /*override fun drawableStateChanged() {
        super.drawableStateChanged()
        Log.e("adsf", "${thumb.isStateful}")
        if (thumb.isStateful) {
            thumb.state = drawableState
        }
        invalidate()

    }*/

    // 배경
    private fun backgroundDraw(canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = progressBackgroundColor

        canvas.drawRect(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat(), paint)
    }

    private fun progressDraw(canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = progressColor

        val wid = TransWidth(canvas.width)
        canvas.drawRect(0.toFloat(), 0.toFloat(), wid, canvas.height.toFloat(), paint)

        thumbDraw(canvas)
    }

    // thumb Draw
    private fun thumbDraw(canvas: Canvas) {

        thumb.setBounds(TransWidth(canvas.width).toInt(), 0, TransWidth(canvas.width).toInt() + 10, canvas.height)
        thumb.draw(canvas)
    }

    // 퍼센테이지 구하기
    private fun TransWidth(defaultWidth: Int): Float {
        val percent = (progress.toFloat() / maxProgress.toFloat()) * 100
        val width = (defaultWidth.toFloat() / 100) * percent
        thumbX = width
        return width.toFloat()
    }
    private fun changeProgress(){
        val a = (thumbX / 1440) * 100
        val b = (maxProgress / 100) *a
        progress = b.toInt()
        invalidate()
    }

    private fun thumbPosition(x: Float) {
        Log.e("thumbPosition", "$progress $x $thumbX ${TransWidth(x.toInt())}")
        if (thumbX + 10 > x || thumbX - 10 < x) {
            thumbX = x
            changeProgress()

        }
    }


}