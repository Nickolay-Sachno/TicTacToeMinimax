class AgentMinimax {
    companion object{
        fun agentMinimax(state: GameState) : Pair<Int, Int>{

            return alphaBeta(state)
        }

        private fun alphaBeta(state: GameState): Pair<Int, Int> {
            return Pair(0,0)
        }
    }
}