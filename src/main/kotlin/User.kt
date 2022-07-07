import kotlin.random.Random

class User(
    override val cellType: CellType
) : Player {

    override fun getNextMove(gameStateTest: GameStateTest): Pair<Int, Int> {
        val (row,col) = getInputFromUser(gameStateTest)
        Display.toConsole("Player's $cellType move: ($row,$col)")
        return Pair(row,col)
    }

    private fun getInputFromUser(gameStateTest: GameStateTest): Pair<Int, Int> {
        while (true){
            Display.toConsole("Please enter a number for row: ", delay = 0)
            val row = readLine()
            Display.toConsole("Please enter a number for column: ", delay = 0)
            val col = readLine()
            if(!isValidMove(gameStateTest,row,col)){
                Display.toConsole("Wrong input")
                return getRandomNextMove(gameStateTest)
            } else{
                if(row != null && col != null)
                    return Pair(row.toInt(), col.toInt())
            }
        } // end get input loop
    }

    private fun getRandomNextMove(gameStateTest: GameStateTest): Pair<Int, Int> {
        while (true){
            val row = Random.nextInt(gameStateTest.getGridSize())
            val col = Random.nextInt(gameStateTest.getGridSize())
            if(gameStateTest.getCell(row,col).content == gameStateTest.notVisitedCell.content)
                return Pair(row,col)
        }
    }

    private fun isValidMove(gameStateTest: GameStateTest, row: String?, col: String?): Boolean {
        if (row != null && col != null) {
            when{
                (row.isEmpty() || col.isEmpty() ||
                        !row.all { char -> char.isDigit()} || !col.all { char -> char.isDigit() } ||
                        row.toInt() < 0 || col.toInt() < 0 ||
                        row.toInt() > gameStateTest.getGridSize() - 1 || col.toInt() > gameStateTest.getGridSize() - 1 ||
                        gameStateTest.getCell(row.toInt(),col.toInt()) != gameStateTest.notVisitedCell
                        ) -> return false
            }
            return true
        }
        return false
    }
}