package Spark.Basics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window

object WindowFunctions {
  def main(args: Array[String]):Unit ={
    val spark = SparkSession.builder.appName("Window Functions").master("local[*]").getOrCreate()

    val pathToCSV = "/home/adityaksi301/Documents/DataSets/Practice/sales_performance.csv"
    val salesDF = spark.read
      .option("header","true")
      .option("inferSchema","true")
      .csv(pathToCSV)
      .withColumn("sale_date",col("sale_date").cast("date"))

    //runnning total per salesperson
    val runningTotalWindow = Window
      .partitionBy("salesperson")
      .orderBy("sale_date")
      .rowsBetween(Window.unboundedPreceding, Window.currentRow) //from first row to current row

    //applying the window function
    val runningTotalDF = salesDF.withColumn(
      "running_total",sum("amount").over(runningTotalWindow)
    )
    runningTotalDF.show()


  }

}
