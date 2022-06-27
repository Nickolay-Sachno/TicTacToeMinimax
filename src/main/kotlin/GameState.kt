class GameState(
    var gridSize: Int = 3, // default TicTacToe
    var grid: Grid = Grid(gridSize),
    var turn: CellType = CellType.CROSS,
    val playerTurn: CellType = CellType.CROSS,
    val agentTurn: CellType = CellType.CIRCLE,
    val notVisited: CellType = CellType.EMPTY
) {
    override fun toString(): String {
        return "It's $turn turn:\n$grid"
    }

    fun copy() : GameState{
        var copyGameState = GameState()
        copyGameState.turn = this.turn
        copyGameState.grid = this.grid.copy()
        return copyGameState
    }

    fun isWinState(gameState: GameState, cellType: CellType):Boolean{
        return isHorizontalWin(cellType) || isVerticalWin(cellType) || isDiagonalWin(cellType)
    }

    fun isStandOff(): Boolean {
        grid.matrix.forEach { row ->
            row.forEach { cell ->
                if(cell.content == notVisited)
                    return false
            }
        }
        return true
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

    fun isMovesLeft(): Boolean {
        return grid.isMovesLeft()
    }
}