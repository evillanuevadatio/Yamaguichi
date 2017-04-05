package com.mx.datio

import java.util.Date

import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils


object ETL {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ETL")
    val ssc = new StreamingContext(conf, Seconds(1))

    // hostname:port for Kafka brokers, not Zookeeper
    val kafkaParams = Map("metadata.broker.list" -> "localhost:9092")
    val topics = Set("chartrix")

    val dsTransactions = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](  ssc, kafkaParams, topics)

    //val d1 = dsTransactions.flatMap(_._2.split(","))
    //val d1 = dsTransactions.flatMap( line => line._2.split(","))
    val d1 = dsTransactions.map( line => line._2.split(","))


    //d1.filter(  current =>  valu[4] < 0 )
    val epoch = System.currentTimeMillis / 1000
    d1.saveAsTextFiles("/home/evillanueva/tmp/d1"+ epoch )

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }
}
