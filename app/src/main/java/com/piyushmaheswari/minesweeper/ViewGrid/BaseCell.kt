package com.piyushmaheswari.minesweeper.ViewGrid

import android.content.Context
import android.view.View

import com.piyushmaheswari.minesweeper.GameEngine

abstract class BaseCell(context: Context) : View(context) {

    var value: Int = 0
        set(value) {
            isBomb = false
            isRevealed = false
            isClicked = false
            isFlagged = false

            if (value == -1) {
                isBomb = true
            }

            field = value
        }
    var isBomb: Boolean = false
    var isRevealed: Boolean = false
        private set
    var isClicked: Boolean = false
        private set
    var isFlagged: Boolean = false

    var xPos: Int = 0
        private set
    var yPos: Int = 0
        private set
    var position: Int = 0
        private set

    fun setRevealed() {
        isRevealed = true
        invalidate()
    }

    fun setClicked() {
        this.isClicked = true
        this.isRevealed = true

        invalidate()
    }

    fun setPosition(x: Int, y: Int) {
        this.xPos = x
        this.yPos = y

        this.position = y * GameEngine.WIDTH + x

        invalidate()
    }

}