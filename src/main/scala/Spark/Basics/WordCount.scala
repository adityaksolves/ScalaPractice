package Spark.Basics
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {

    val sparkConfiguration = new SparkConf()
    sparkConfiguration.setAppName("WordCount")
    sparkConfiguration.setMaster("local[*]")
    val sc = new SparkContext(sparkConfiguration)
    val path = "/home/adityaksi301/IdeaProjects/ScalaPractice/src/main/scala/Spark/wordcount.txt"

    val linesRDD = sc.textFile(path)

    // Splitting lines into words using a flatMap
    val allWords = linesRDD.flatMap(line => line.split(" "))

    // Cleaning the words
    val cleanWords = allWords.map(word => {
      val lower = word.toLowerCase()
      val onlyLetters = lower.replaceAll("[^a-z]", "")
      onlyLetters
    })

    //Filtering out empty strings
    val filteredWords = cleanWords.filter(word => word.isEmpty == false)

    //Mapping
    val wordPairs = filteredWords.map(word => (word, 1))

    // Adding the count
    val finalCounts = wordPairs.reduceByKey((accum, value) => accum + value)

    //Sorting by the count
    val sortedResults = finalCounts.sortBy(tuple => tuple._2, ascending = false)

    // Collecting the top 10
    val top10Results = sortedResults.take(10)

    //Printing using a standard loop
    println("Top 10 words from textfile : ")
    for (result <- top10Results) {
      val word = result._1
      val count = result._2
      println(word + " : " + count)
    }

    sc.stop()
  }
}