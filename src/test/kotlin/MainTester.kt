fun main() {
    val agent = NewAgent.createAgent(
        cellType = CellType.CIRCLE,
        diff = AgentDiff.MEDIUM
    )

    val gameStateTest = GameStateTest(
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
        notVisitedCell = Cell()
    )

    Manager.play(gameStateTest)

    val temp = gameStateTest.currentTurn()
}