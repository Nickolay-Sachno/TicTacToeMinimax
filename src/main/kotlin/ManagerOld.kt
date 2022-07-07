/** This is a manager class, which controls the game flow */
class ManagerOld {

    companion object{

        // if user enters incorrect input, random move will be added
        private var numOfTries = 3

        fun play(gameStateOld: GameStateOld){
            Display.toConsole("Welcome to TicTacToe game!\n$gameStateOld")
            while(true) {
                when (gameStateOld.currentTurn) {
                    gameStateOld.playerCellType -> {
                        playerMakeMove(gameStateOld);
                        gameStateOld.currentTurn = gameStateOld.agentCellType;
                        if(gameStateOld.isWinState(gameStateOld.playerCellType)){
                            Display.toConsole("Player wins!")
                            return
                        }
                    }
                    gameStateOld.agentCellType -> {
                        agentMakeMove(gameStateOld);
                        gameStateOld.currentTurn = gameStateOld.playerCellType;
                        if(gameStateOld.isWinState(gameStateOld.agentCellType)){
                            Display.toConsole("Agent wins!")
                            return
                        }
                    }
                }
                if(gameStateOld.isStandOff()) {
                    Display.toConsole("It's standoff =)")
                    return
                }
            } // end play loop
        }

        fun playerMakeMove(gameStateOld: GameStateOld) {

            while (true){
                Display.toConsole("Please make a move for row: ", delay = 0)
                val row = readLine()
                Display.toConsole("Please make a move for column: ", delay = 0)
                val col = readLine()
                if(!validateMove(gameStateOld, row, col)) {
                    numOfTries--
                    Display.toConsole("Incorrect input, please try again")
                    if(numOfTries < 1){
                        var (row_random,col_random) = AgentOld.randomMove(gameStateOld)
                        gameStateOld.setNewCell(Cell(row_random, col_random, CellType.CROSS)) // TODO convert to setContent()
                        Display.toConsole("Was randomly chosen ($row_random,$col_random) to ${gameStateOld.playerCellType}", delay = 0)
                        gameStateOld.currentTurn = gameStateOld.agentCellType
                        Display.toConsole(gameStateOld)
                        return
                    }
                    continue
                } // end validate move
                if(row != null && col != null){
                    gameStateOld.setNewCell(Cell(row.toInt(),col.toInt(), CellType.CROSS))
                    Display.toConsole("Player changed ($row,$col) to ${gameStateOld.playerCellType}")
                }
                break
            } // end move
            gameStateOld.currentTurn = gameStateOld.agentCellType
            Display.toConsole(gameStateOld)
        }


        fun agentMakeMove(gameStateOld: GameStateOld) {
            val (row,col) = AgentOld.agentMove(gameStateOld)
            gameStateOld.setNewCell(Cell(row, col, CellType.CIRCLE))
            Display.toConsole("Agent changed cell ($row,$col) to ${gameStateOld.agentCellType}")
            gameStateOld.currentTurn = gameStateOld.playerCellType
            Display.toConsole(gameStateOld)
        }

        fun validateMove(gameStateOld: GameStateOld, row: String?, col: String?): Boolean {
            if (row != null && col != null) {
                when{
                    (row.isEmpty() || col.isEmpty() ||
                            !row.all { char -> char.isDigit()} || !col.all { char -> char.isDigit() } ||
                            row.toInt() < 0 || col.toInt() < 0 ||
                            row.toInt() > gameStateOld.gridSize - 1 || col.toInt() > gameStateOld.gridSize - 1 ||
                            gameStateOld.grid.matrix[row.toInt()][col.toInt()].content != CellType.EMPTY
                            ) -> return false
                }
                return true
            }
            return false
        }
    }
}