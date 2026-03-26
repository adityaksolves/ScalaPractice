// Scala Program on command line argument
object CMDExample
{
  // Main method
  def main(args: Array[String]): Unit =
  {
    println("Scala Command Line Argument Example")

    /* You can pass anything at runtime
       that will be printed on the console */

    for(arg <- args)
    {
      println(arg)
    }
  }
}