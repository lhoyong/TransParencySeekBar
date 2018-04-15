package com.github.lhoyong.transparencyseekbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class TransParencySeekBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ProgressListener {

    // attr
    private val a = attrs.let { context.obtainStyledAttributes(attrs, R.styleable.TransParencySeekBar, defStyleAttr, defStyleAttr) }

    // thumb & touch X Position
    private var thumbX: Float = 0F
    private var touchX: Float = 0F

    val thumb: Drawable = a.getDrawable(R.styleable.TransParencySeekBar_thumb)
            ?: resources.getDrawable(R.drawable.thumb)

    var progressBackgroundColor: Int = a.getColor(R.styleable.TransParencySeekBar_backgroundColor, context.resources.getColor(R.color.backgroundColor))

    var progressColor: Int = a.getColor(R.styleable.TransParencySeekBar_progressColor, context.resources.getColor(R.color.progressColor))

    var maxProgress: Int = a.getInteger(R.styleable.TransParencySeekBar_maxProgress, 100)

    var progress: Int = a.getInteger(R.styleable.TransParencySeekBar_progress, 0)

    /* true: use Thumb  */
    var mEnabled: Boolean = a.getBoolean(R.styleable.TransParencySeekBar_enable, true)

    /*thumb Click
    *  true :  MotionEvent Pressed thumb Visible
    *  false : thumb Always Visible */
    var thumbClickable: Boolean = a.getBoolean(R.styleable.TransParencySeekBar_thumbClickable, false)

    init {
        a?.recycle()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> isPressed = true
                MotionEvent.ACTION_MOVE -> if (isPressed) changeProgress(event.x)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isPressed = false
                    touchX = 0F
                }
            }
        }
        return mEnabled
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)

        thumbX = getThumbPosition()
        setMeasuredDimension(width, height)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackground(canvas)
        drawProgress(canvas)
        if (mEnabled) {
            if(!thumbClickable) drawThumb(canvas)
            else if(thumbClickable&& isPressed) drawThumb(canvas)
        }
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()

    }

    override fun invoke(progress: Int) {
        this.progress = progress    // Progress Update
        thumbX = getThumbPosition() // thumb Position Update
        invalidate()
    }

    // draw Background
    private fun drawBackground(canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = progressBackgroundColor

        canvas.drawRect(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat(), paint)
    }

    // draw Progress
    private fun drawProgress(canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = progressColor

        canvas.drawRect(0.toFloat(), 0.toFloat(), thumbX, canvas.height.toFloat(), paint)
    }

    // thumb Draw
    private fun drawThumb(canvas: Canvas) {
        thumb.setBounds(thumbX.toInt(), 0, thumbX.toInt() + 10, canvas.height)
        thumb.draw(canvas)
    }

    // get Thumb X Position
    private fun getThumbPosition(): Float {
        val percent = (progress.toFloat() / maxProgress.toFloat()) * 100
        return (measuredWidth.toFloat() / 100) * percent
    }

    // change Progress
    private fun changeProgress(x: Float) {
        if (touchX == 0F) touchX = x
        else if (thumbX < 0) {
            thumbX = 0F
            progress = 0
        } else if (thumbX > measuredWidth) {
            thumbX = measuredWidth.toFloat()
            progress = maxProgress
        } else {
            progress = ((maxProgress.toFloat() / 100) * ((thumbX / measuredWidth) * 100)).toInt()
            thumbX += x - touchX

            if (x >= touchX) touchX += x - touchX
            else touchX -= touchX - x
        }

        invalidate()
    }

}