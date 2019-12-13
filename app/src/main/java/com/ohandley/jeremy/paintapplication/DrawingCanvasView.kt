package com.ohandley.jeremy.paintapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


class DrawingCanvasView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var path: Path = Path()
    private var paint: Paint = Paint()
    // LinkedHashMap ensures new lines aren't drawn behind old lines, by keeping an order
    private var paints: LinkedHashMap<Path, Paint>? = LinkedHashMap()

    private var posX = 0f
    private var posY = 0f
    private var startX = 0f
    private var startY = 0f
    private val defaultStroke = 10f
    private val defaultStyle = Paint.Style.STROKE

    private fun applyPaintDefaults(paint: Paint) {
        paint.apply {
            strokeWidth = defaultStroke
            style = defaultStyle

        }
    }

    fun setPaintColor(color: Int) {
        paint.color = color
        applyPaintDefaults(paint)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paints!!.forEach {
            canvas.drawPath(it.key, it.value)
        }

    }

    private fun downAction(x: Float, y: Float) {
        path.moveTo(x, y)
        posX = x
        posY = y
        paints!![path] = paint
    }

    private fun moveAction(x: Float, y: Float) {
        path.quadTo(posX, posY, (x + posX) / 2, (y + posY) / 2)
        posX = x
        posY = y
    }

    private fun upAction() {
        path.lineTo(posX, posY)
        // draw a dot
        if (startX == posX && startY == posY) {
            path.lineTo(posX, posY + 2)
            path.lineTo(posX + 1, posY + 2)
            path.lineTo(posX + 1, posY)
        }
        val currColor = paint.color
        path = Path()
        paint = Paint()
        paint.color = currColor
        applyPaintDefaults(paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> downAction(x, y)
            MotionEvent.ACTION_MOVE -> moveAction(x, y)
            MotionEvent.ACTION_UP -> upAction()
        }
        invalidate()
        return true
    }

    fun clearCanvas() {
        paints!!.clear()
        path.reset()
        invalidate()

    }
}

