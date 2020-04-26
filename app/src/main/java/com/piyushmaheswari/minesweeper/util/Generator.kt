package com.piyushmaheswari.minesweeper.util

import java.util.Random

object Generator {

    fun generate(bombNumber: Int, width: Int, height: Int): Array<IntArray> {
        var bombnumber = bombNumber

        // Random for generating numbers
        val r = Random()

        val grid = Array(width) { IntArray(height) }
        for (x in 0 until width) {
            grid[x] = IntArray(height)
        }

        while (bombnumber > 0) {
            val x = r.nextInt(width)
            val y = r.nextInt(height)

            // -1 is the bomb
            if (grid[x][y] != -1) {
                grid[x][y] = -1
                bombnumber--
            }
        }
        calculateNeigbours(grid, width, height)

        return grid
    }

    private fun calculateNeigbours(grid: Array<IntArray>, width: Int, height: Int) {
        for (x in 0 until width) {
            for (y in 0 until height) {
                grid[x][y] = getNeighbourNumber(grid, x, y, width, height)
            }
        }

    }

    private fun getNeighbourNumber(grid: Array<IntArray>, x: Int, y: Int, width: Int, height: Int): Int {
        if (grid[x][y] == -1) {
            return -1
        }

        var count = 0

        if (isMineAt(grid, x - 1, y + 1, width, height)) count++ // top-left
        if (isMineAt(grid, x, y + 1, width, height)) count++ // top
        if (isMineAt(grid, x + 1, y + 1, width, height)) count++ // top-right
        if (isMineAt(grid, x - 1, y, width, height)) count++ // left
        if (isMineAt(grid, x + 1, y, width, height)) count++ // right
        if (isMineAt(grid, x - 1, y - 1, width, height)) count++ // bottom-left
        if (isMineAt(grid, x, y - 1, width, height)) count++ // bottom
        if (isMineAt(grid, x + 1, y - 1, width, height)) count++ // bottom-right

        return count
    }

    private fun isMineAt(grid: Array<IntArray>, x: Int, y: Int, width: Int, height: Int): Boolean {
        return if (x >= 0 && y >= 0 && x < width && y < height) {
            grid[x][y] == -1
        } else false
    }

}
