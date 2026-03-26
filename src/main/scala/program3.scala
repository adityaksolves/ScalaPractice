
object GFG
{

  // Main method
  def main(args: Array[String])
  {

    // applying range method
    val x = Range(1, 8)

    // Including upper bound
    val y = x.inclusive

    // applying 'to' method
    val z = 1 to 8

    // Displays true if both the
    // methods are equal
   // println(y == z)

    for(i <- x) println(i)
    println()
    for(i <- y) println(i)
    println()
    for(i <- z) println(i)
  }
}
