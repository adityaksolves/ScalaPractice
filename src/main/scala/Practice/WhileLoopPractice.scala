package Practice

object WhileLoopPractice {
  def main(args: Array[String]): Unit = {
    var i = 0
    var sum = 0
    while(i<=100){
      sum += i
      i += 1
    }
    println(s"Sum of numbers from 1 to 100 = $sum")
  }
}
