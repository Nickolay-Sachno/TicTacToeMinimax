fun main() {
    var gameState = GameState()

    println("Test new minimax!")
    for(i in 0 until 2) {
        gameState.grid = gameState.grid.fillRandomGridWithSpaces()
        println("${gameState.grid}")
        println("Are any moves left? ${gameState.isMovesLeft()}")
        gameState.grid = gameState.grid.fillRandomGrid()
        println("${gameState.grid}")
        println("Are any moves left? ${gameState.isMovesLeft()}")
    }
    //val bestMove = GFG.findBestMove(gameState.grid.gridToCharMatrix())
}