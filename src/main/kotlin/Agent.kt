import java.lang.Float.*
import kotlin.random.Random

/** Used alphabetic algorithm for agent path finding */
class AgentMinimax {

    companion object{
        fun agentMove(gameState: GameState) : Pair<Int, Int>{
            //return randomMove(gameState)
            return minimax(gameState)
        }


        fun randomMove(gameState: GameState) : Pair<Int,Int>{
            while (true){
                var i = Random.nextInt(gameState.grid.matrix.size)
                var j = Random.nextInt(gameState.grid.matrix.size)
                if(gameState.grid.matrix[i][j]?.content == gameState.notVisited)
                    return Pair(i,j)
            }
        }

        /** ###########################################                   ########################################### */
        /** ########################################### private functions ########################################### */
        /** ###########################################                   ########################################### */

        private fun minimax(gameState: GameState) : Pair<Int, Int>{
            val bestMove = GFG1.findBestMove(gameState.grid.gridToCharMatrix())
            return Pair(bestMove.row, bestMove.col)
        }
    }
}