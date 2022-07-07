class Manager {
    companion object {
        fun play(gameStateTest: GameStateTest) {

            while (true) {
                Display.toConsole("$gameStateTest")
                val currentPlayer = gameStateTest.getNextPlayer()
                val (row, col) = currentPlayer.getNextMove(gameStateTest)
                gameStateTest.setCell(Cell(
                    row = row,
                    col = col,
                    content = currentPlayer.cellType
                ))
                if (gameStateTest.isWinState(Cell(content = currentPlayer.cellType))
                    || gameStateTest.isStandOff()){
                    when{
                        gameStateTest.isWinState(Cell(content = currentPlayer.cellType)) ->
                            Display.toConsole("Player ${currentPlayer.cellType} WINS!!!!!")
                        gameStateTest.isStandOff() ->
                            Display.toConsole("It's a standoff -_-")
                    }
                    return
                }
            } // end game loop
        }
    }
}