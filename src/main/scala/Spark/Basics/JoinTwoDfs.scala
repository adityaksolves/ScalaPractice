package Spark.Basics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object JoinTwoDfs {
  def main(args: Array[String]): Unit ={
    val spark = SparkSession.builder.appName("Join two dfs").master("local[*]").getOrCreate()
    import spark.implicits._

    val customersDF = Seq(
      (101, "Aditya", "aditya@example.com", "India"),
      (102, "Sarah", "sarah@example.com", "USA"),
      (103, "Chen", "chen@example.com", "China"),
      (104, "Fatima", "fatima@example.com", "UAE"),
      (105, "Bruno", "bruno@example.com", "Brazil")
    ).toDF("customer_id", "name", "email", "country")


    val ordersDF = Seq(
      (1, 101, "Laptop", 1200),
      (2, 101, "Mouse", 25),
      (3, 102, "Smartphone", 800),
      (4, 101, "Keyboard", 50),
      (5, 103, "Tablet", 300),
      (6, 101, "Monitor", 200),
      (7, 102, "Case", 30),
      (8, 102, "Charger", 20),
      (9, 103, "Headphones", 100),
      (10, 102, "Smartwatch", 250)
    ).toDF("order_id", "customer_id", "product", "amount")

    val innerJoin = customersDF.join(ordersDF, "customer_id")
    println("Inner Join (only matches):")
    innerJoin.show(3)

    val leftJoin = customersDF.join(ordersDF, Seq("customer_id"), "left")
    println("Left Join (shows everyone, including those with 0 orders):")
    leftJoin.show(5)

    val nonBuyers = customersDF.join(ordersDF, Seq("customer_id"), "left_anti")
    println("Left Anti Join (The 'Zero Orders' Club):")
    nonBuyers.show()

    val optimizedJoined = ordersDF.join(broadcast(customersDF), "customer_id")

    val finalResult = optimizedJoined
      .groupBy("customer_id", "name", "email")
      .agg(count("order_id").as("order_count"))
      .filter(col("order_count") > 3)
      .select("name", "email", "order_count")

    println("--- Final Result: Customers with > 3 orders ---")
    finalResult.show()

    println("--- Query Plan ---")
    finalResult.explain()


  }

}
