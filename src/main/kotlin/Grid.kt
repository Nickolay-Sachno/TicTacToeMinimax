/** Control the grid of TicTacToe. The default dimension is 3, but can be modified */
class Grid(val dim : Int = 3) {
    init {
        var grid = Array(dim) { arrayOfNulls<Cell>(dim)}
        for(i in 0 until grid.size){
            for(j in 0 until grid[0].size){
                grid[i][j] = Cell(i,j)
            }
        }
    }
}