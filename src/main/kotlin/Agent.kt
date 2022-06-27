import java.lang.Float.*
import kotlin.random.Random

/** Class Agent is used as computer player
 *
 * You can use two algorithms for the agent, in the fun agentMove select the random choice
 * for the coordinates or using the minimax algorithm
 * */
class Agent {

    companion object{
        /** Return the coordinates (i,j) for the Agent's move  */
        fun agentMove(gameState: GameState) : Pair<Int, Int>{
            //return randomMove(gameState)
            return minimax(gameState)
        }


        fun randomMove(gameState: GameState) : Pair<Int,Int>{
            while (true){
                var i = Random.nextInt(gameState.grid.matrix.size)
                var j = Random.nextInt(gameState.grid.matrix.size)
                if(gameState.grid.matrix[i][j].content == gameState.notVisited)
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