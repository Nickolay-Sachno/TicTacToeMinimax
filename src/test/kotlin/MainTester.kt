fun main() {
    val agent = NewAgent.createAgent(
        cellType = CellType.CIRCLE,
        diff = AgentDiff.MEDIUM
    )

    val gameStateTest = GameStateTest(
        grid = Grid(3),
        listOfPlayers = mutableListOf(
            NewAgent.createAgent(
                cellType = CellType.CIRCLE,
                diff = AgentDiff.MEDIUM
            ) as Player,
            User(
                cellType = CellType.CROSS
            ) as Player
        ),
        notVisitedCell = Cell()
    )

    Manager.play(gameStateTest)

    val temp = gameStateTest.currentTurn()
}