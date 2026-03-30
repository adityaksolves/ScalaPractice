package Spark.Basics
import org.apache.spark.sql.{SparkSession, SaveMode}
import org.apache.spark.sql.functions._
object ParquetPractice {
  def main(args: Array[String]): Unit ={
    val spark = SparkSession.builder.appName("Parquet Practice").master("local[*]").getOrCreate()

    import spark.implicits._

    val path ="/home/adityaksi301/Documents/DataSets/Practice/company_sales.csv"
    val salesDF = spark.read.option("header","true")
      .option("inferSchema","true")
      .csv(path)

    val processedDF = salesDF.withColumn("amount_with_tax", col("amount")*1.1)

    val outputPath = "ScalaPractice/src/scala/Spark/output/sales_parquet"

    processedDF.write.mode(SaveMode.Overwrite)
      .partitionBy("year","region")
      .parquet(outputPath)

    println(s"Data written successfully to ${outputPath}")

    val readDF = spark.read.parquet(outputPath)

    val filteredDF = readDF.filter(col("year") === 2024)

    println("data for 2024 :")
    filteredDF.show()


    println("Query PLan : ")
    filteredDF.explain()

  }


}
