/** This is a manager class, which controls the game flow */
class Manager {

    companion object{

        private val playerTurn = CellType.CROSS
        private val agentTurn = CellType.CIRCLE

        private var numOfTries = 3

        fun play(gameState: GameState){
            when(gameState.turn){
                playerTurn -> playerMakeMove(gameState)
                agentTurn -> agentMakeMove(gameState)
            }
        }

        private fun playerMakeMove(gameState: GameState) {

            var i = 0
            var j = 0

            while (numOfTries > 0){
                println("Please make a move for i: ")
                var move = readLine()
                i = move?.toInt()!!
                if(!validateMove(gameState, i)) {
                    numOfTries--
                    println("Number of tries: $numOfTries")
                    continue
                }
                println("Please make a move for j: ")
                move = readLine()
                j = move?.toInt()!!
                if(!validateMove(gameState, j)) {
                    numOfTries--
                    println("Number of tries: $numOfTries")
                    continue
                }
                break
            }
            gameState.grid.matrix[i][j]?.content = CellType.CROSS
        }


        private fun agentMakeMove(gameState: GameState) {
            val (i,j) = AgentMinimax.agentMinimax(gameState)
        }

        private fun validateMove(gameState: GameState, i: Int): Boolean {
            return i <= gameState.grid.matrix.size - 1 && i > -1 && i is Int
        }
    }
}