/** This is a manager class, which controls the game flow */
class Manager {

    companion object{

        // if user enters incorrect input, random move will be added
        private var numOfTries = 3

        fun play(gameState: GameState){
            Display.toConsole("Welcome to TicTacToe game!\n$gameState")
            while(true) {
                //if(steps == 0) return
                when (gameState.currentTurn) {
                    gameState.playerCellType -> {
                        playerMakeMove(gameState);gameState.currentTurn = gameState.agentCellType; if(gameState.isWinState(gameState.playerCellType)){
                            Display.toConsole("Player wins!")
                            return
                        }
                    }
                    gameState.agentCellType -> {
                        agentMakeMove(gameState);gameState.currentTurn = gameState.playerCellType; if(gameState.isWinState(gameState.agentCellType)){
                            Display.toConsole("Agent wins!")
                            return
                        }
                    }
                }
                if(gameState.isStandOff()) {
                    Display.toConsole("It's standoff =)")
                    return
                }
            }
        }

        fun playerMakeMove(gameState: GameState) {

            while (true){
                Display.toConsole("Please make a move for row: ", delay = 0)
                var row = readLine()
                Display.toConsole("Please make a move for column: ", delay = 0)
                var col = readLine()
                if(!validateMove(gameState, row, col)) {
                    numOfTries--
                    Display.toConsole("Incorrect input, please try again")
                    if(numOfTries < 1){
                        var (k,o) = Agent.randomMove(gameState)
                        gameState.grid.matrix[k][o].content = gameState.playerCellType
                        Display.toConsole("Was randomly chosen ($k,$o) to ${gameState.playerCellType}", delay = 0)
                        gameState.currentTurn = gameState.agentCellType
                        Display.toConsole(gameState)
                        return
                    }
                    continue
                }

                gameState.grid.matrix[row?.toInt()!!][col?.toInt()!!].content = gameState.playerCellType
                Display.toConsole("Player changed ($row,$col) to ${gameState.playerCellType}")
                break
            }
            gameState.currentTurn = gameState.agentCellType
            Display.toConsole(gameState)
        }


        fun agentMakeMove(gameState: GameState) {
            val (row,col) = Agent.agentMove(gameState)
            gameState.grid.matrix[row][col].content = gameState.agentCellType
            Display.toConsole("Agent changed cell ($row,$col) to ${gameState.agentCellType}")
            gameState.currentTurn = gameState.playerCellType
            Display.toConsole(gameState)
        }

        fun validateMove(gameState: GameState, row: String?, col: String?): Boolean {
            if (row != null && col != null) {
                when{
                    (row.isEmpty() || col.isEmpty() ||
                            !row.all { char -> char.isDigit()} || !col.all { char -> char.isDigit() } ||
                            row.toInt() < 0 || col.toInt() < 0 ||
                            row.toInt() > gameState.gridSize - 1 || col.toInt() > gameState.gridSize - 1 ||
                            gameState.grid.matrix[row.toInt()][col.toInt()].content != CellType.EMPTY
                            ) -> return false
                }
                return true
            }
            return false
        }
    }
}