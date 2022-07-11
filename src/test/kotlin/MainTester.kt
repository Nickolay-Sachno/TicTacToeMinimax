fun main() {

    val gameState = GameState(
        grid = Grid(3),
        listOfPlayers = mutableListOf(
            User(
                cellType = CellType.CROSS
            ) as Player,
            NewAgent.createAgent(
                cellType = CellType.CIRCLE,
                diff = AgentDiff.MEDIUM
            ) as Player
        ),
        notVisitedCell = Cell(),
        listOfMoves = mutableListOf()
    )

    Manager.play(gameState)
}