interface Player {
    val cellType : CellType

    fun getNextMove(gameStateTest: GameStateTest) : Pair<Int,Int>
}