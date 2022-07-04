fun main() {
    var gameState = GameState()
    Display.toConsole(gameState, delay = 1000)
    // run a game : player makes random move, agent minimax
    while (gameState.isMovesLeft()){
        when(gameState.currentTurn){
            gameState.playerCellType -> {
                val (row, col) = Agent.randomMove(gameState)
                gameState.grid.matrix[row][col].content = gameState.playerCellType
                Display.toConsole("Player changed ($row,$col)")
                gameState.currentTurn = gameState.agentCellType
                Display.toConsole(gameState, delay = 1000)
                if (gameState.isWinState(gameState.playerCellType)) {
                    Display.toConsole("Player wins!")
                    return
                }
            }
            gameState.agentCellType -> {
                val (row, col) = Agent.agentMove(gameState)
                gameState.grid.matrix[row][col].content = gameState.agentCellType
                gameState.currentTurn = gameState.playerCellType
                Display.toConsole(gameState, delay = 1000)
                if (gameState.isWinState(gameState.agentCellType)) {
                    Display.toConsole("Agent wins!")
                    return
                }
            }
        }
    }
    Display.toConsole("It's a standoff.")
}