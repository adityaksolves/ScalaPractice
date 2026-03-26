package Practice

object FizzBuzzProgram {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 50){
      if (i % 3 == 0 && i % 5 == 0){
        println("FizzBuzz")
      }
      else if(i % 3 == 0 && i % 5 != 0){
        println("Fizz")
      }
      else if(i % 5 == 0 && i % 3 != 0){
        println("Buzz")
      }
      else{
        println(i)
      }
    }
  }

}
