package Spark.Basics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
object ReadCSVFile {
  def main(args: Array[String]): Unit ={
    val spark = SparkSession.builder().appName("Reading from CSV files").master("local[*]").getOrCreate()

    val filepath = "/home/adityaksi301/Documents/DataSets/Practice/products.csv"
    val df = spark.read.option("header","true").option("inferSchema","true").csv(filepath)

    println(df.printSchema())

    val dfManualSchema = StructType(Array(
      StructField("product_id",StringType, true),
      StructField("product_name",StringType, true),
      StructField("category",StringType, true),
      StructField("base_unit_price",DoubleType, true)
    ))

    val prodDf = spark.read.option("header","true").schema(dfManualSchema).csv(filepath)

    val expensiveElectronics = prodDf.filter(col("category") === "Electronics" && col("base_unit_price") > 500.0)

    expensiveElectronics.show()

    val budgetItems = prodDf.filter("base_unit_price < 50.0")
      .select("product_id","product_name")
    budgetItems.show()


    val dfWithTax = df.withColumn("total_price",col("base_unit_price") *1.10)
    dfWithTax.select("product_name","base_unit_price", "total_price").show()

  }
}
