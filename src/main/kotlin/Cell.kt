/** This class represents a cell in the grid of TicTacToe */
class Cell (val row : Int, val col : Int){
    var content : CellType = CellType.EMPTY
    fun clear(){
        this.content = CellType.EMPTY
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Cell)
            this.content == other.content
        else
            super.equals(other)
    }
}