/** This is a manager class, which controls the game flow */
class Manager {

    companion object{

        val playerTurn = CellType.CROSS
        val agentTurn = CellType.CIRCLE

        private var numOfTries = 3

        fun play(gameState: GameState){
            var steps = 2
            while(true) {
                //if(steps == 0) return
                when (gameState.turn) {
                    playerTurn -> {
                        playerMakeMove(gameState);gameState.turn = gameState.agentTurn; if(gameState.isWinState(gameState, gameState.playerTurn)){
                            println("Player wins!")
                            return
                        }
                    }
                    agentTurn -> {
                        agentMakeMove(gameState);gameState.turn = gameState.playerTurn; if(gameState.isWinState(gameState, gameState.playerTurn)){
                            println("Agent wins!")
                            return
                        }
                    }
                }
                if(gameState.isStandOff(gameState)) {
                    println("It's standoff =)")
                    return
                }
                steps --
            }
        }
        /** ###########################################                   ########################################### */
        /** ########################################### private functions ########################################### */
        /** ###########################################                   ########################################### */

        private fun playerMakeMove(gameState: GameState) {
            println("playerMakeMove()")
            var i = 0
            var j = 0

            while (true){
                println(gameState)
                println("Please make a move for i: ")
                var move = readLine()
                i = move?.toInt()!!
                if(!validateMove(gameState, i)) {
                    numOfTries--
                    println("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = AgentMinimax.randomMove(gameState)
                        gameState.grid.matrix[k][o]?.content = gameState.playerTurn
                        println("Player changed ($k,$o) to ${gameState.playerTurn}")
                        gameState.turn = gameState.agentTurn
                        println(gameState)
                        return
                    }
                    continue
                }
                println("Please make a move for j: ")
                move = readLine()
                j = move?.toInt()!!
                if(!validateMove(gameState, j)) {
                    numOfTries--
                    println("Number of tries: $numOfTries")
                    if(numOfTries < 1){
                        var (k,o) = AgentMinimax.randomMove(gameState)
                        gameState.grid.matrix[k][o]?.content = gameState.playerTurn
                        println("Player changed ($k,$o) to ${gameState.playerTurn}")
                        gameState.turn = gameState.agentTurn
                        println(gameState)
                        return
                    }
                    continue
                }
                gameState.grid.matrix[i][j]?.content = gameState.playerTurn
                break
            }
            println("Player changed ($i,$j) to ${gameState.playerTurn}")
            gameState.turn = gameState.agentTurn
            println(gameState)
        }


        private fun agentMakeMove(gameState: GameState) {
            println("agentMakeMove()")
            val (i,j) = AgentMinimax.agentMinimax(gameState)
            gameState.grid.matrix[i][j]?.content = gameState.agentTurn
            println("Agent changed ($i,$j) to ${gameState.agentTurn}")
            gameState.turn = gameState.playerTurn
            println(gameState)
        }

        private fun validateMove(gameState: GameState, i: Int): Boolean {
            println("validateMove()")
            return i <= gameState.grid.matrix.size - 1 && i > -1 && i is Int
        }
    }
}