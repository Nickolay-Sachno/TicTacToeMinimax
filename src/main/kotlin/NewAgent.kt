import kotlin.random.Random

abstract  class NewAgent(
    val cellType: CellType,
    val diff : AgentDiff
){
    companion object{
        fun createAgent(cellType: CellType, diff: AgentDiff) = when(diff) {
            AgentDiff.EASY -> RandomAgent(cellType,diff)
            AgentDiff.MEDIUM -> MinimaxAgent(cellType, diff)
        }
    }
}

class RandomAgent(
    cellType: CellType,
    diff: AgentDiff
) : NewAgent(cellType, diff), Player{
    override fun getNextMove(gameStateTest: GameStateTest) : Pair<Int, Int>{
        while (true){
            val row = Random.nextInt(gameStateTest.getGridSize())
            val col = Random.nextInt(gameStateTest.getGridSize())
            if(gameStateTest.getCell(row,col).content == gameStateTest.notVisitedCell.content) {
                Display.toConsole("Player's $cellType move: ($row,$col)")
                return Pair(row, col)
            }
        }
    }
}

class MinimaxAgent(
    cellType: CellType,
    diff: AgentDiff
) : NewAgent(cellType,diff), Player{
    override fun getNextMove(gameStateTest: GameStateTest) : Pair<Int, Int>{
        val bestMove = GFG1.findBestMove(gameStateTest.grid.gridToCharMatrix())
        Display.toConsole("Player's $cellType move: (${bestMove.row}, ${bestMove.col})")
        return Pair(bestMove.row, bestMove.col)
    }
}


