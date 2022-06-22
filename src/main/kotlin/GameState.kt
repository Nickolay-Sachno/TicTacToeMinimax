class GameState(
    var grid: Grid = Grid(3),
    var turn: CellType = CellType.CROSS,
    val playerTurn: CellType = CellType.CROSS,
    val agentTurn : CellType = CellType.CIRCLE
) {
    override fun toString(): String {
        return "It's $turn turn:\n$grid"
    }

    fun isWinState(gameState: GameState, cellType: CellType):Boolean{
        return horizontal(gameState, cellType) || vertical(gameState, cellType) || diagonal(gameState, cellType)
    }

    /** ###########################################                   ########################################### */
    /** ########################################### private functions ########################################### */
    /** ###########################################                   ########################################### */

    /** Win states */
    private fun horizontal(gameState: GameState, cellType: CellType) : Boolean{
        val n = gameState.grid.matrix.size
        var seq = 0
        for(i in 0 until n){
            for(j in 0 until n){
                if(gameState.grid.matrix[i][j]?.content == cellType)
                    seq ++
            }
            if(seq == n)
                return true
            else{
                seq = 0
            }
        }
        return false
    }

    private fun vertical(gameState: GameState, cellType: CellType) : Boolean{
        val n = gameState.grid.matrix.size
        var seq = 0
        for(i in 0 until n){
            for(j in 0 until n){
                if(gameState.grid.matrix[j][i]?.content == cellType)
                    seq ++
            }
            if(seq == n)
                return true
            else{
                seq = 0
            }
        }
        return false
    }

    private fun diagonal(gameState: GameState, cellType: CellType) : Boolean{
        var matrix = gameState.grid.matrix
        val n = gameState.grid.matrix.size
        var seq = 0

        // check from left top corner to right bottom
        for(i in 0 until n){
            if(cellType == matrix[i][i]?.content) {
                seq++
            }
        }

        if(seq == n)
            return true
        else{
            seq = 0
        }

        // check from right top corner to right bottom
        var j = n-1
        for(i in 0 until n){
            if (matrix[i][j]?.content == cellType) {
                seq++
            }
            j--
        }
        if(seq == n)
            return true
        return false
    }
}