class GameStateOld(
    var gridSize: Int = 3, // default TicTacToe
    var grid: Grid = Grid(gridSize),
    var currentTurn: CellType = CellType.CROSS,
    val playerCellType: CellType = CellType.CROSS,
    val agentCellType: CellType = CellType.CIRCLE,
    val notVisitedCellType: CellType = CellType.EMPTY
) {
    override fun toString(): String {
        return "$grid\nIt's $currentTurn player turn\n "
    }

    fun copy() : GameStateOld{
        return GameStateOld(
            gridSize = this.gridSize,
            grid = this.grid.copy(),
            currentTurn = this.currentTurn,
            playerCellType = this.playerCellType,
            agentCellType = this.agentCellType,
            notVisitedCellType = this.notVisitedCellType
        )
    }

    fun isWinState(cellType: CellType):Boolean{
        return isHorizontalWin(cellType) || isVerticalWin(cellType) || isDiagonalWin(cellType)
    }

    fun isStandOff(): Boolean {
        grid.matrix.forEach { row ->
            row.forEach { cell ->
                if(cell.content == notVisitedCellType)
                    return false
            }
        }
        return true
    }

    fun isMovesLeft(): Boolean {
        return grid.isMovesLeft()
    }

    fun setNewCell(cell: Cell){
        grid.setNewCell(cell)
    }

    fun getCell(row:Int, col:Int) : Cell {
        return grid.matrix[row][col]
    }

    /** ###########################################                   ########################################### */
    /** ########################################### private functions ########################################### */
    /** ###########################################                   ########################################### */

    /** Win states */
    private fun isHorizontalWin(cellType: CellType) : Boolean{
         return grid.matrix.any { row ->
            row.all { cell ->  cell.content == cellType }
        }
    }

    private fun isVerticalWin(cellType: CellType) : Boolean{
        val n = gridSize
        var seq = 0
        for(i in 0 until n){
            for(j in 0 until n){
                if(grid.matrix[j][i].content == cellType)
                    seq ++
            }
            if(seq == n) {
                return true
            }
            else{
                seq = 0
            }
        }
        return false
    }

    private fun isDiagonalWin(cellType: CellType) : Boolean{
        val n = gridSize
        var seq = 0

        // check from left top corner to right bottom
        for(i in 0 until n){
            if(cellType == grid.matrix[i][i].content) {
                seq++
            }
        }

        if(seq == n) {
            return true
        }
        else{
            seq = 0
        }

        // check from right top corner to right bottom
        var j = n-1
        for(i in 0 until n){
            if (grid.matrix[i][j].content == cellType) {
                seq++
            }
            j--
        }
        if(seq == n) {
            return true
        }
        return false
    }
}