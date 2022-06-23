import kotlin.random.Random

/** Control the grid of TicTacToe. The default dimension is 3, but can be modified */
class Grid(
    private val dim : Int,
    var matrix: Array<Array<Cell?>> = Array(dim) { arrayOfNulls<Cell>(dim) }
    ) {

    init {
        for(i in matrix.indices){
            for(j in 0 until matrix[0].size){
                matrix[i][j] = Cell(i,j)
            }
        }
    }

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
        var grid = Grid(this.dim)
        for(i in 0 until dim){
            for(j in 0 until dim){
                grid.matrix[i][j] = this.matrix[i][j]?.copy()
            }
        }
        return grid
    }

    fun isMovesLeft() : Boolean {
        for(i in matrix.indices){
            for(j in matrix.indices){
                if(matrix[i][j]?.content == CellType.EMPTY)
                    return true
            }
        }
        return false
    }

    fun fillRandomGrid() : Grid{
        val listOfMoves = listOf<CellType>(CellType.CIRCLE, CellType.CROSS)
        var grid = this.copy()
        for(i in 0 until dim){
            for(j in 0 until dim){
                var temp = Random.nextInt(listOfMoves.size)
                grid.matrix[i][j]?.content = listOfMoves[temp]
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
                grid.matrix[i][j]?.content = listOfMoves[temp]
            }
        }
        return grid
    }

    fun gridToCharMatrix() : Array<CharArray>{
        var charArr = Array(dim) { CharArray(dim) }
        for(i in 0 until dim){
            for(j in 0 until dim){
                charArr[i][j] = this.matrix[i][j]?.content!!?.chr.lowercaseChar()
            }
        }
        return charArr
    }
}