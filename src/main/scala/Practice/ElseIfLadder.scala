package Practice

object ElseIfLadder {
  def main(args: Array[String]): Unit = {
    val marks = 82
    var grade = ""
    if (marks >= 90){
      grade = "A"
    }
    else if(marks >= 75 && marks < 90){
      grade = "B"
    }
    else if(marks >= 60 && marks < 75){
      grade = "C"
    }
    else if(marks >= 40 && marks < 60){
      grade = "D"
    }
    else if(marks < 40){
      grade = "F"
    }
    else{
      println(s"Please enter valid numeric value.")
    }
    print(s"Marks: $marks => Grade: $grade")
  }
}
