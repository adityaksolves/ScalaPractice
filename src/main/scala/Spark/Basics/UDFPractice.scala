package Spark.Basics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object UDFPractice {
  def main(args: Array[String]): Unit ={
    val spark = SparkSession.builder.appName("UDF Practice").master("local[*]").getOrCreate()

    import spark.implicits._

    val scoreDF = Seq(
      ("Aditya", 45),
      ("Sarah", 88),
      ("Chen", 72),
      ("Fatima", 95),
      ("Bruno", 30)
    ).toDF("student_name", "score")

    def classifyScore(score: Int): String ={
      if (score >= 85) "High"
      else if(score >= 50) "Medium"
      else "Low"
    }

    val classifyUDF = udf(classifyScore _)

    val resultDF = scoreDF.withColumn("category", classifyUDF(col("score")))

    println("==== Dataframe API Result =====")
    resultDF.show()

    spark.udf.register("sql_classify", classifyScore _)
    scoreDF.createOrReplaceTempView("student_scores")

    val sqlResult = spark.sql("""SELECT student_name, score, sql_classify(score) as sql_category FROM student_scores""")

    println("==== Spark SQL Result =====")
    sqlResult.show()


  }

}
