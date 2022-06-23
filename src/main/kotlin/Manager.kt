/** This is a manager class, which controls the game flow */
class Manager {

    companion object{

        private var numOfTries = 3

        fun play(gameState: GameState){
            Display.toConsole("Welcome to TicTacToe game!\n$gameState")
            var steps = 2
            while(true) {
                //if(steps == 0) return
                when (gameState.turn) {
                    gameState.playerTurn -> {
                        playerMakeMove(gameState);gameState.turn = gameState.agentTurn; if(gameState.isWinState(gameState, gameState.playerTurn)){
                            Display.toConsole("Player wins!")
                            return
                        }
                    }
                    gameState.agentTurn -> {
                        agentMakeMove(gameState);gameState.turn = gameState.playerTurn; if(gameState.isWinState(gameState, gameState.agentTurn)){
                            Display.toConsole("Agent wins!")
                            return
                        }
                    }
                }
                if(gameState.isStandOff(gameState)) {
                    Display.toConsole("It's standoff =)")
                    return
                }
                steps --
            }
        }
        /** ###########################################                   ########################################### */
        /** ########################################### private functions ########################################### */
        /** ###########################################                   ########################################### */

        private fun playerMakeMove(gameState: GameState) {
            var i = 0
            var j = 0

            while (true){
                Display.toConsole("Please make a move for row: ")
                var move = readLine()
                i = move?.toInt()!!
                if(!validateMove(gameState, i)) {
                    numOfTries--
                    Display.toConsole("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = AgentMinimax.randomMove(gameState)
                        gameState.grid.matrix[k][o]?.content = gameState.playerTurn
                        Display.toConsole("Player changed cell ($k,$o) to ${gameState.playerTurn}")
                        gameState.turn = gameState.agentTurn
                        Display.toConsole(gameState)
                        return
                    }
                    continue
                }
                Display.toConsole("Please make a move for column: ")
                move = readLine()
                j = move?.toInt()!!
                if(!validateMove(gameState, j)) {
                    numOfTries--
                    Display.toConsole("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = AgentMinimax.randomMove(gameState)
                        gameState.grid.matrix[k][o]?.content = gameState.playerTurn
                        Display.toConsole("Player changed cell ($k,$o) to ${gameState.playerTurn}")
                        gameState.turn = gameState.agentTurn
                        Display.toConsole(gameState)
                        return
                    }
                    continue
                }
                gameState.grid.matrix[i][j]?.content = gameState.playerTurn
                break
            }
            Display.toConsole("Player changed ($i,$j) to ${gameState.playerTurn}")
            gameState.turn = gameState.agentTurn
            Display.toConsole(gameState)
        }


        private fun agentMakeMove(gameState: GameState) {
            val (i,j) = AgentMinimax.agentMove(gameState)
            gameState.grid.matrix[i][j]?.content = gameState.agentTurn
            Display.toConsole("Agent changed cell ($i,$j) to ${gameState.agentTurn}")
            gameState.turn = gameState.playerTurn
            Display.toConsole(gameState)
        }

        private fun validateMove(gameState: GameState, i: Int): Boolean {
            return i <= gameState.grid.matrix.size - 1 && i > -1 && i is Int
        }
    }
}