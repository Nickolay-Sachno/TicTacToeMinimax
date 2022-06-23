class Display {
    companion object{
        fun toConsole(str:Any, delay:Int = 500){
            Thread.sleep(delay.toLong())
            println(str.toString())
        }
    }
}