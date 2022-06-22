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
}