// The blueprint
class program1 {
  def greet(): Unit = {
    println("Hello from class!")
  }
}

// The object that USES the blueprint
object Runner {
  def main(args: Array[String]): Unit = {
    val p = new program1()  // Creating an actual instance from the blueprint
    p.greet()               // Now using it
  }
}

/**
 *
 */