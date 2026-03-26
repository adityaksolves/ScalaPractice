package Practice

object MultiplicationTable {
  def main(args: Array[String]): Unit = {
    val number = 7
    for (i <- 1 to 10){
      println(s"$number * $i = ${number * i}")
    }
  }

}
