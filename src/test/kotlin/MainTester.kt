fun main() {

    val gameState = GameState(
        grid = Grid(3),
        listOfPlayers = mutableListOf(
            User(
                cellType = CellType.CROSS
            ) as Player,
            Agent.createAgent(
                cellType = CellType.CIRCLE,
                diff = AgentDifficulties.MEDIUM
            ) as Player
        ),
        notVisitedCell = Cell(),
        listOfMoves = mutableListOf()
    )

    Manager.play(gameState)
}