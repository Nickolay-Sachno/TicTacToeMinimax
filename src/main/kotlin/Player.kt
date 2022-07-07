interface Player {
    val cellType : CellType

    /** Return a coordinates (i,j) based on the game state */
    fun getNextMove(gameStateTest: GameStateTest) : Pair<Int,Int>
}