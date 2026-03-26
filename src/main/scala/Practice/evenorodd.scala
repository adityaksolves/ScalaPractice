package Practice

object EvenOrOdd {
  def main(args: Array[String]): Unit = {
    val number = 15
    if (number % 2 != 0){
      println(s"${number} is odd.")
    }
    else{
      println(s"$number is even")
    }
  }
}
