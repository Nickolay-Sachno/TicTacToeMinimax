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
                if(gameState.isStandOff()) {
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
                Display.toConsole("Please make a move for row: ", delay = 0)
                var move = readLine()
                if(!validateMove(gameState, move)) {
                    numOfTries--
                    Display.toConsole("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = Agent.randomMove(gameState)
                        gameState.grid.matrix[k][o].content = gameState.playerTurn
                        Display.toConsole("Player changed cell ($k,$o) to ${gameState.playerTurn}", delay = 0)
                        gameState.turn = gameState.agentTurn
                        Display.toConsole(gameState)
                        return
                    }
                    continue
                }
                i = move?.toInt()!!

                Display.toConsole("Please make a move for column: ", delay = 0)
                move = readLine()
                if(!validateMove(gameState, move)) {
                    numOfTries--
                    Display.toConsole("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = Agent.randomMove(gameState)
                        gameState.grid.matrix[k][o].content = gameState.playerTurn
                        Display.toConsole("Player changed cell ($k,$o) to ${gameState.playerTurn}", delay = 0)
                        gameState.turn = gameState.agentTurn
                        Display.toConsole(gameState)
                        return
                    }
                    continue
                }
                j = move?.toInt()!!

                gameState.grid.matrix[i][j].content = gameState.playerTurn
                break
            }
            Display.toConsole("Player changed ($i,$j) to ${gameState.playerTurn}")
            gameState.turn = gameState.agentTurn
            Display.toConsole(gameState)
        }


        private fun agentMakeMove(gameState: GameState) {
            val (i,j) = Agent.agentMove(gameState)
            gameState.grid.matrix[i][j].content = gameState.agentTurn
            Display.toConsole("Agent changed cell ($i,$j) to ${gameState.agentTurn}")
            gameState.turn = gameState.playerTurn
            Display.toConsole(gameState)
        }

        private fun validateMove(gameState: GameState, i: String?): Boolean {
            if (i != null) {
                when{
                    i.isEmpty() -> return false
                    !i.all { char -> char.isDigit()} -> return false
                    i.toInt() < 0 -> return false
                    i.toInt() > gameState.grid.matrix.size - 1 -> return false
                }
                return true
            }
            return false
        }
    }
}