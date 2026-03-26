package Practice

object Factorial {
  def main(args: Array[String]): Unit = {
    val original_num = 5
    var num = 5
    var fact = 1
    do{
      fact = fact * num
      num = num - 1
    }
    while(num > 0)

    println(s"Factorial of $original_num = $fact")
  }

}
