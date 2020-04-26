package com.piyushmaheswari.minesweeper.ViewGrid

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View

import androidx.core.content.ContextCompat

import com.piyushmaheswari.minesweeper.GameEngine
import com.piyushmaheswari.minesweeper.R

class Cell(context: Context, x: Int, y: Int) : BaseCell(context), View.OnClickListener, View.OnLongClickListener {

    init {

        setPosition(x, y)

        setOnClickListener(this)
        setOnLongClickListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onClick(v: View) {
        GameEngine.getInstance().click(xPos, yPos)
    }

    override fun onLongClick(v: View): Boolean {
        GameEngine.getInstance().flag(xPos, yPos)

        return true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("Minesweeper", "Cell::onDraw")
        drawButton(canvas)

        if (isFlagged) {
            drawFlag(canvas)
        } else if (isRevealed && isBomb && !isClicked) {
            drawNormalBomb(canvas)
        } else {
            if (isClicked) {
                if (value == -1) {
                    drawBombExploded(canvas)
                } else {
                    drawNumber(canvas)
                }
            } else {
                drawButton(canvas)
            }
        }
    }

    private fun drawBombExploded(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bomb_exploded)!!
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawFlag(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.flag)!!
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawButton(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button)!!
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawNormalBomb(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bomb_normal)!!
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawNumber(canvas: Canvas) {
        var drawable: Drawable? = null

        when (value) {
            0 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_0)
            1 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_1)
            2 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_2)
            3 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_3)
            4 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_4)
            5 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_5)
            6 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_6)
            7 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_7)
            8 -> drawable = ContextCompat.getDrawable(context, R.drawable.number_8)
        }

        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }


}