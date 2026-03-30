package Spark.Basics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object GroupByAggregation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("GroupByAggregations").master("local[*]").getOrCreate()

    val pathToDf = "/home/adityaksi301/Documents/DataSets/Practice/sales_data.csv"
    val sales_df = spark.read.option("header","true").option("inferSchema","true").csv(pathToDf)

    //find total sales per region
    val sales_per_region = sales_df.groupBy(col("region")).agg(sum(col("amount")).alias("Sales"))
    sales_per_region.show()

    val avg_order_val_df = sales_df.groupBy(col("product")).agg(avg(col("amount")).alias("Avg order Value"))
    avg_order_val_df.show()

    val top_products = sales_df.groupBy("product").agg(sum(col("amount")).as("total_revenue")).orderBy(desc("total_revenue")).limit(5)
    top_products.show()
  }

}
