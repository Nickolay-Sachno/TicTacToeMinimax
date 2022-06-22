fun main() {
    val grid = Grid(3)
    println("The Grid:\n$grid")
    grid.matrix[2][1]?.content = CellType.CROSS
    println("grid[2][1] : ${grid.matrix[2][1]}")
    println(grid)

    var gameState = GameState()
    Manager.play(gameState)
}