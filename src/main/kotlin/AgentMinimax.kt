import java.lang.Float.*
import kotlin.random.Random

/** Used alphabetic algorithm for agent path finding */
class AgentMinimax {

    companion object{
        fun agentMove(gameState: GameState) : Pair<Int, Int>{
            //return alphaBeta(gameState)
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

        // we assume here that agent is the maximizer
        private fun evaluate(gameState: GameState) : Float{
            // agent wins
            if(gameState.isWinState(gameState, gameState.agentTurn))
                return 10F
            // player wins
            return if(gameState.isWinState(gameState, gameState.playerTurn))
                -10F
            else{
                0F
            }
        }

        private fun alphaBeta(gameState: GameState): Pair<Int, Int> {
            var bestPair : Pair<Float, Pair<Int, Int>> = Pair(-POSITIVE_INFINITY, Pair(0,0))
            var alpha = -POSITIVE_INFINITY
            var beta = POSITIVE_INFINITY
            val n = gameState.grid.matrix.size
            var maxValue = -POSITIVE_INFINITY

            for(i in 0 until n){
                for(j in 0 until n){
                    if(gameState.grid.matrix[i][j]?.content == gameState.notVisited){
                        var childGameState = gameState.copy()
                        childGameState.grid.matrix[i][j]?.content = gameState.agentTurn
                        var value = alphaBeta(gameState, 9, alpha, beta, true)
                        gameState.grid.matrix[i][j]?.content = gameState.notVisited
                        if(value > maxValue){
                            maxValue = value
                            bestPair = Pair(maxValue,Pair(i,j))
                        }
                    }
                }
            }
            return bestPair.second
        }

        private fun minimax (gameState: GameState) : Pair<Int, Int>{
            var bestPair : Pair<Float, Pair<Int, Int>> = Pair(-POSITIVE_INFINITY, Pair(0,0))
            var depth = 9

            for(i in 0 until gameState.gridSize){
                for(j in 0 until gameState.gridSize){
                    if(gameState.grid.matrix[i][j]?.content != gameState.notVisited)
                        continue
                    var childGameState = gameState.copy()
                    childGameState.grid.matrix[i][j]?.content = gameState.agentTurn
                    var tempResult : Float = minimax(childGameState, depth, true)
                    if(tempResult  > bestPair.first)
                        bestPair = Pair(tempResult, Pair(i,j))
                    }
                }
            return bestPair.second
        }

        private fun alphaBeta(gameState: GameState, depth: Int, a: Float, b: Float, maximizingPlayer: Boolean) : Float{
            var alpha = a
            var beta = b
            // base case -> Cross win / Circle win // standoff
            if(depth == 0 || gameState.isWinState(gameState, gameState.agentTurn) || gameState.isWinState(gameState, gameState.playerTurn) || gameState.isStandOff(gameState)) {
                //println("Base case:\n$gameState")
                when {
                    gameState.isWinState(gameState, gameState.agentTurn) -> return 10F
                    gameState.isWinState(gameState, gameState.playerTurn) -> return -10F
                    gameState.isStandOff(gameState) -> return 0F
                }
            }
            if(maximizingPlayer){
                var value = -POSITIVE_INFINITY
                // develop each child gameState
                for(i in 0 until gameState.grid.matrix.size){
                    for(j in 0 until gameState.grid.matrix.size){
                        // if the cell is already been visited
                        if(gameState.grid.matrix[i][j]?.content != gameState.notVisited)
                            continue
                        var childGameState = gameState.copy()
                        childGameState.grid.matrix[i][j]?.content = gameState.agentTurn
                        value = max(value, alphaBeta(childGameState, depth-1, alpha, beta, false))
                        if(value >= beta)
                            break
                        alpha = max(alpha, value)
                    }
                }
                return value
            }
            else{
                var value = POSITIVE_INFINITY
                for(i in 0 until gameState.grid.matrix.size) {
                    for (j in 0 until gameState.grid.matrix.size) {
                        // if the cell is already been visited
                        if (gameState.grid.matrix[i][j]?.content != gameState.notVisited)
                            continue
                        var childGameState = gameState.copy()
                        childGameState.grid.matrix[i][j]?.content = gameState.agentTurn
                        value = min(value, alphaBeta(childGameState, depth-1, alpha, beta, true))
                        if(value <= alpha)
                            break
                        beta = min(beta, value)
                    }
                }
                return value
            }
        }

        private fun minimax(gameState: GameState, depth:Int, maximizer: Boolean) : Float {
            // base case
            when(evaluate(gameState)){
                10F -> return 10F
                -10F -> return -10F
            }
            if(!gameState.isMovesLeft())
                return 0F

            if(maximizer){
                var value = -POSITIVE_INFINITY
                for(i in 0 until gameState.grid.matrix.size){
                    for( j in 0 until gameState.grid.matrix.size){
                        if(gameState.grid.matrix[i][j]?.content != CellType.EMPTY)
                            continue
                        // make a child node of the current game state
                        var childGameState = gameState.copy()
                        childGameState.grid.matrix[i][j]?.content = gameState.agentTurn
                        value = max(value, minimax(childGameState, depth +1, false))
                    }
                }
                return value
            }
            else{
                var value = POSITIVE_INFINITY
                for(i in 0 until gameState.grid.matrix.size){
                    for( j in 0 until gameState.grid.matrix.size){
                        if(gameState.grid.matrix[i][j]?.content != CellType.EMPTY)
                            continue
                        // make a child node of the current game state
                        var childGameState = gameState.copy()
                        childGameState.grid.matrix[i][j]?.content = gameState.playerTurn
                        value = min(value, minimax(childGameState, depth +1, true))
                    }
                }
                return value
            }
        }
    }
}