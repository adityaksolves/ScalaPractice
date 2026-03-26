package Practice

object fibonacci {
  def main(args: Array[String]): Unit = {
    var first = 0
    var second = 1
    var count = 0

    print("Fibonacci Series: ")
    while (count < 10){
      print(first + " ")
      val next = first + second
      first = second
      second = next
      count = count + 1
    }
  }

}
