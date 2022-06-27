import kotlin.random.Random

/** Control the grid of TicTacToe. The default dimension is 3, but can be modified */
class Grid(
    private val dim : Int,
    var matrix: Array<Array<Cell>> = kotlin.run {
        Array(dim) { row ->
            Array(dim) { col ->
                Cell(row, col)
            }
        }
    }
) {

    override fun toString(): String {
        var temp : String = ""
        for( i in matrix.indices){
            for(j in matrix[0].indices){
                temp += "${matrix[i][j].toString()} "
            }
            temp += "\n"
        }
        return temp
    }

    fun copy() : Grid{
        val grid = Grid(this.dim)
        grid.matrix.forEachIndexed { row, arrayOfCells ->
            arrayOfCells.forEachIndexed { column, cell ->
                grid.matrix[row][column] = this.matrix[row][column].copy()
            }
        }
        return grid
    }

    fun isMovesLeft() : Boolean {
        return matrix.any { row ->
            row.any { cell ->
                cell.content == CellType.EMPTY
            }
        }
    }

    fun fillRandomGrid() : Grid{
        val listOfMoves = listOf<CellType>(CellType.CIRCLE, CellType.CROSS)
        var grid = this.copy()
        for(i in 0 until dim){
            for(j in 0 until dim){
                var temp = Random.nextInt(listOfMoves.size)
                grid.matrix[i][j].content = listOfMoves[temp]
            }
        }
        return grid
    }

    fun fillRandomGridWithSpaces() : Grid{
        val listOfMoves = listOf<CellType>(CellType.CIRCLE, CellType.CROSS, CellType.EMPTY)
        var grid = this.copy()
        for(i in 0 until dim){
            for(j in 0 until dim){
                var temp = Random.nextInt(listOfMoves.size)
                grid.matrix[i][j].content = listOfMoves[temp]
            }
        }
        return grid
    }

    fun gridToCharMatrix() : Array<CharArray>{
        var charArr = Array(dim) { CharArray(dim) }
        for(i in 0 until dim){
            for(j in 0 until dim){
                charArr[i][j] = this.matrix[i][j].content!!.chr.lowercaseChar()
            }
        }
        return charArr
    }
}