class Manager {
    companion object {
        fun play(gameStateTest: GameStateTest) {
            Display.toConsole("Welcome to TicTacToe !")
            while (true) {
                Display.toConsole("$gameStateTest")
                val currentPlayer = getCurrentPlayerAngMakeMove(gameStateTest)
                val currentPlayerCellType = currentPlayer.cellType
                if (gameStateTest.isWinState(Cell(content = currentPlayerCellType))
                    || gameStateTest.isStandOff()){
                    when{
                        gameStateTest.isWinState(Cell(content = currentPlayerCellType)) ->
                            Display.toConsole("Player $currentPlayerCellType WINS!!!!!")
                        gameStateTest.isStandOff() ->
                            Display.toConsole("It's a standoff -_-")
                    }
                    Display.toConsole("$gameStateTest")
                    return
                }
            } // end game loop
        }

        private fun getCurrentPlayerAngMakeMove(gameStateTest: GameStateTest)  : Player{
            gameStateTest.prevGameStateTest = gameStateTest.copy()
            val currentPlayer = gameStateTest.getNextPlayer()
            val (row, col) = currentPlayer.getNextMove(gameStateTest)
            gameStateTest.setCell(Cell(
                row = row,
                col = col,
                content = currentPlayer.cellType
            ))
            gameStateTest.listOfMoves.add(Pair(row,col))
            return currentPlayer
        }
    }
}