import kotlin.random.Random

/** Class Agent is used as computer player
 *
 * You can use two algorithms for the agent, in the fun agentMove select the random choice
 * for the coordinates or using the minimax algorithm
 * */
class AgentOld {

    companion object{
        /** Return the coordinates (i,j) for the Agent's move  */
        fun agentMove(gameStateOld: GameStateOld) : Pair<Int, Int>{
            //return randomMove(gameState)
            return minimax(gameStateOld)
        }


        fun randomMove(gameStateOld: GameStateOld) : Pair<Int,Int>{
            while (true){
                var i = Random.nextInt(gameStateOld.grid.matrix.size)
                var j = Random.nextInt(gameStateOld.grid.matrix.size)
                if(gameStateOld.grid.matrix[i][j].content == gameStateOld.notVisitedCellType)
                    return Pair(i,j)
            }
        }

        /** ###########################################                   ########################################### */
        /** ########################################### private functions ########################################### */
        /** ###########################################                   ########################################### */

        private fun minimax(gameStateOld: GameStateOld) : Pair<Int, Int>{
            val bestMove = GFG1.findBestMove(gameStateOld.grid.gridToCharMatrix())
            return Pair(bestMove.row, bestMove.col)
        }
    }
}