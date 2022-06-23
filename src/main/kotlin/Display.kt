class Display {
    companion object{
        fun toConsole(str:Any){
            Thread.sleep(500)
            println(str.toString())
        }
    }
}