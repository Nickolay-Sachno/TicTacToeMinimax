import java.lang.Float.POSITIVE_INFINITY

/** Used alphabetic algorithm for agent path finding */
class AgentMinimax {
    companion object{
        fun agentMinimax(state: GameState) : Pair<Int, Int>{
            return alphaBeta(state)
        }

        private fun alphaBeta(gameState: GameState): Pair<Int, Int> {
            var bestPair : Pair<Float, Pair<Int, Int>> = Pair(-POSITIVE_INFINITY, Pair(0,0))
            return bestPair.second
        }

        private fun random(gameState: GameState, agentCellType: CellType, notVisitedCellType: CellType): GameState {
            for(i in 0 until gameState.grid.matrix.size){
                for(j in 0 until gameState.grid.matrix.size){
                    if(gameState.grid.matrix[i][j]?.content == notVisitedCellType){
                        gameState.grid.matrix[i][j]?.content = agentCellType
                        return gameState
                    }
                }
            }
            return gameState
        }
    }
}