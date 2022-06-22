/** Control the grid of TicTacToe. The default dimension is 3, but can be modified */
class Grid(
    private val dim : Int,
    var grid: Array<Array<Cell?>> = Array(dim) { arrayOfNulls<Cell>(dim)
    }) {

    init {
        for(i in grid.indices){
            for(j in 0 until grid[0].size){
                grid[i][j] = Cell(i,j)
            }
        }
    }

    override fun toString(): String {
        var temp : String = ""
        for( i in grid.indices){
            for(j in grid[0].indices){
                temp += "${grid[i][j].toString()} "
            }
            temp += "\n"
        }
        return temp
    }
}