package Spark.Basics

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object WordCountWithDF {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("WordCountDataFrame")
      .master("local[*]")
      .getOrCreate()

    val filePath = "/home/adityaksi301/IdeaProjects/ScalaPractice/src/main/scala/Spark/wordcount.txt"
    val rawDF = spark.read.text(filePath)

    val wordsDF = rawDF
      .select(explode(split(col("value"), " ")).as("word"))
      .filter(col("word") =!= "")

    val wordCounts = wordsDF
      .groupBy("word")
      .count()
      .orderBy(desc("count"))

    wordCounts.show()

    spark.stop()
  }
}