fun main() {
    val grid = Grid(3)
    println("The Grid:\n$grid")
    grid.matrix[2][1]?.content = CellType.CROSS
    println("grid[2][1] : ${grid.matrix[2][1]}")
    println(grid)

    var gameState = GameState()
    gameState.grid.matrix[2][0]?.content = CellType.CROSS
    gameState.grid.matrix[1][1]?.content = CellType.CROSS
    gameState.grid.matrix[0][2]?.content = CellType.CROSS
    println(gameState)
    println("Horizontal check: ${gameState.horizontal(gameState, CellType.CROSS)}")
    println(gameState)
    println("Vertical check: ${gameState.vertical(gameState, CellType.CROSS)}")
    println(gameState)
    println("Diagonal check: ${gameState.diagonal(gameState, CellType.CROSS)}")



}