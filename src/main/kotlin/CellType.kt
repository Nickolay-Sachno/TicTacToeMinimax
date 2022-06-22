/** Simple enum class to control the content of each cell */
enum class CellType(val chr : Char) {
    EMPTY('-'),
    CROSS('X'),
    CIRCLE('O')
}