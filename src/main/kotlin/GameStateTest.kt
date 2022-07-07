class GameStateTest(
    val grid: Grid,
    val listOfPlayers: MutableList<Player>,
    val notVisitedCell: Cell) {

    override fun toString(): String {
        return "$grid\nTurn: ${currentTurn().cellType}"
    }

    fun getNextPlayer() : Player{
        val tempPlayer = listOfPlayers[0]
        listOfPlayers.removeAt(0)
        listOfPlayers.add(tempPlayer)
        return tempPlayer
    }

    fun currentTurn() : Player{
        return listOfPlayers[0]
    }

    fun getCell(row : Int, col : Int): Cell {
        return grid.matrix[row][col]
    }

    fun getGridSize() : Int {
        return grid.matrix.size
    }

    fun setCell(cell: Cell){
        grid.matrix[cell.row][cell.col] = cell
    }

    fun isWinState(cell: Cell):Boolean{
        return isHorizontalWin(cell) || isVerticalWin(cell) || isDiagonalWin(cell)
    }

    private fun isDiagonalWin(cell: Cell): Boolean {
        val n = getGridSize()
        var seq = 0

        // check from left top corner to right bottom
        for(i in 0 until n){
            if(cell.content == getCell(i,i).content) {
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
            if (cell.content == getCell(i,j).content) {
                seq++
            }
            j--
        }
        if(seq == n) {
            return true
        }
        return false
    }

    private fun isVerticalWin(cell: Cell): Boolean {
        val n = getGridSize()
        var seq = 0
        for(i in 0 until n){
            for(j in 0 until n){
                if(getCell(i,j).content == cell.content)
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

    private fun isHorizontalWin(cell: Cell): Boolean {
        return grid.matrix.any { row ->
            row.all { it ->  it.content == cell.content }
        }
    }

    fun isStandOff(): Boolean {
        grid.matrix.forEach { row ->
            row.forEach { cell ->
                if(cell.content == notVisitedCell.content)
                    return false
            }
        }
        return true
    }
}