package com.m1ku.baseproject.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.m1ku.baseproject.R
import com.m1ku.ktutils.ext.dp2px
import com.m1ku.ktutils.ext.sp2px

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/5/28
 * Description: 带text文本的进度条
 */
class TextProgressBar : View {

    private val colorArray = intArrayOf(
            Color.parseColor("#4a6cfb"),
            Color.parseColor("#819af2")
    )
    private val positionArray = floatArrayOf(
            0f,
            1f
    )
    private var mBarHeight = 8
    private var mTrackColor = Color.parseColor("#d6eafe")
    private var mTextColor = Color.GRAY
    private var mTextSize = 13
    private lateinit var mTrackPaint: Paint
    private lateinit var mTextPaint: Paint
    private lateinit var mBarPaint: Paint
    private var bottomText = listOf<String>()
    private var topText = listOf<String>()
    private var mDivisionWidth = 0 //每一个分区的宽度
    private var mMaxProgress = 0f
    private var mCurrentProgress = 0f
    private val animateTime = 2000L
    private lateinit var dotBitmap: Bitmap

    //使用this委托给两个参数的构造函数
    constructor(context: Context) : this(context, null)

    //使用this委托给三个参数的构造函数
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    //使用super调用父类的构造函数，初始化其基类
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TextProgressBar)
        mBarHeight = ta.getDimensionPixelOffset(R.styleable.TextProgressBar_barHeight, context.dp2px(mBarHeight))
        mTrackColor = ta.getColor(R.styleable.TextProgressBar_trackColor, mTrackColor)
        mTextColor = ta.getColor(R.styleable.TextProgressBar_barTextColor, mTextColor)
        mTextSize = ta.getDimensionPixelSize(R.styleable.TextProgressBar_barTextSize, context.sp2px(mTextSize))
        ta.recycle()

        initPaint()
    }

    private fun initPaint() {
        dotBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_progress_dot)
        mTrackPaint = Paint().apply {
            color = mTrackColor
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = mBarHeight.toFloat()
            style = Paint.Style.FILL_AND_STROKE
        }

        mTextPaint = Paint().apply {
            color = mTextColor
            isAntiAlias = true
            style = Paint.Style.FILL
            textSize = mTextSize.toFloat()
        }

        mBarPaint = Paint().apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = mBarHeight.toFloat()
            style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode == MeasureSpec.AT_MOST) {
            height = context.dp2px(50)
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mDivisionWidth = width / (bottomText.size - 1)
        canvas?.drawColor(Color.WHITE)
        //画底部进度滑轨
        canvas?.drawLine((mBarHeight / 2).toFloat(), height / 2.toFloat(),
                (width - mBarHeight / 2).toFloat(), height / 2.toFloat(), mTrackPaint)

        val currentX = (mCurrentProgress / mMaxProgress) * width - mTextPaint.measureText("15")
        //为滑轨设置渐变色
        val gradient = LinearGradient(
                0f,
                height / 2.toFloat(),
                currentX,
                height / 2.toFloat(),
                colorArray,
                positionArray,
                Shader.TileMode.REPEAT
        )
        mBarPaint.shader = gradient
        //画进度条滑轨
        canvas?.drawLine((mBarHeight / 2).toFloat(), height / 2.toFloat(),
                currentX, height / 2.toFloat(), mBarPaint)


        //画进度条圆点
        canvas?.drawBitmap(dotBitmap, currentX - mBarHeight / 2,
                height / 2f - (dotBitmap.height / 2), mBarPaint)
        //画底部文字
        if (bottomText.isNotEmpty()) {
            bottomText.forEachIndexed { index, s ->
                val textWidth = mTextPaint.measureText(s)
                if (index == 0) {
                    canvas?.drawText(s,
                            (index * mDivisionWidth).toFloat(),
                            (height / 2 + mBarHeight + 30).toFloat(),
                            mTextPaint)
                } else {
                    canvas?.drawText(s,
                            (index * mDivisionWidth).toFloat() - textWidth,
                            (height / 2 + mBarHeight + 30).toFloat(),
                            mTextPaint)
                }
            }
        }
        //画顶部文字
        val textWidth = mTextPaint.measureText("15")
        if (topText.isNotEmpty()) {
            topText.forEachIndexed { index, s ->
                canvas?.drawText(s,
                        (mDivisionWidth / 2 + mDivisionWidth * index).toFloat() - textWidth,
                        ((height / 2 - 30).toFloat()),
                        mTextPaint)
            }
        }


    }

    fun setBottomText(bottomText: List<String>) {
        this.bottomText = bottomText
        invalidate()
    }

    fun setTopText(topText: List<String>) {
        this.topText = topText
        invalidate()
    }

    fun setMaxProgress(maxProgress: Float) {
        this.mMaxProgress = maxProgress
    }

    fun setCurrentProgress(progress: Float) {
        val animator = ValueAnimator.ofFloat(0f, progress)
        animator.addUpdateListener {
            mCurrentProgress = it.animatedValue as Float
            invalidate()
        }
        animator.interpolator = AccelerateInterpolator()
        animator.duration = animateTime
        animator.start()
    }
}