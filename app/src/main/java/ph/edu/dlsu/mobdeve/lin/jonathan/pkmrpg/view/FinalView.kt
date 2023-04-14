package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.content.Context
import android.view.View

class FinalView(context: Context) : View(context) {
    private val paint = Paint()

    init {
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 100f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        canvas.drawText("The End", centerX, centerY, paint)
    }
}