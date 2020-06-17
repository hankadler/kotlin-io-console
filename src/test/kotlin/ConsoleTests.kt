import com.hankadler.io.Console

fun main() {
    // Sets Console settings.
    Console.clearScreen = true

    // Runs tests.
    testAsk()
    testSelect()
}

fun testAsk() {
    // Input
    val string = Console.ask(prompt = "Enter string: ", valueType = Console.STRING_TYPE, valueDefault = "Valhalla")
    val i = Console.ask(prompt = "Enter i: ", valueType = Console.INT_TYPE, valueDefault = 0)
    val n = Console.ask(prompt = "Enter n: ", valueType = Console.DOUBLE_TYPE, valueDefault = 0.0)
    val bool = Console.ask(prompt = "Enter bool: ", valueType = Console.BOOLEAN_TYPE, valueDefault = false)

    // Output
    println("\nstring = $string <${string?.javaClass}>")
    println("i      = $i <${i?.javaClass}>")
    println("n      = $n <${n?.javaClass}>")
    println("bool   = $bool <${bool?.javaClass}>")
}

fun testSelect() {
    // Input
    val color = Console.select("\nSelect color", listOf<String>("Red", "Green", "Blue"))

    // Output
    println("\nColor = $color <${color.javaClass}>")
}
