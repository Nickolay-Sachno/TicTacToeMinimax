fun main() {
    var gameState = GameState()

    println("Test new minimax!")
    for(i in 0 until 10) {
        gameState.grid = gameState.grid.fillRandomGridWithSpaces()
        println("Test board:\n$gameState")
        val bestMove = GFG.findBestMove(gameState.grid.gridToCharMatrix())
        println("Best move is:\nRow - ${bestMove.row}, Col - ${bestMove.col}")
    }
    //val bestMove = GFG.findBestMove(gameState.grid.gridToCharMatrix())
}