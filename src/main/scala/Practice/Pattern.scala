package Practice

object Pattern {
  def main(args: Array[String]): Unit = {
    for ( i <- 1 to 5){
      for (j <- 1 to i){
        print(j)
      }
      println()
    }
  }

}
