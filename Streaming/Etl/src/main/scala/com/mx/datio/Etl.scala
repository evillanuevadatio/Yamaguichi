package com.mx.datio

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.dstream._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils

import _root_.kafka.serializer.DefaultDecoder
import _root_.kafka.serializer.StringDecoder


object Etl{
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("KafkaETL")
        // Create a StreamingContext with a 1 second batch size
        val ssc = new StreamingContext(conf, Seconds(2))
        
        val kafkaConf = Map(
            "metadata.broker.list" -> "localhost:9092",
            "zookeeper.connect" -> "localhost:2181",
            "group.id" -> "kafka-stream",
            "zookeeper.connection.timeout.ms" -> "1000"
        )
        val lines = KafkaUtils.createStream[Array[Byte], String, DefaultDecoder, StringDecoder](
            ssc,
            kafkaConf,
            Map("chartrix" -> 1),
            StorageLevel.MEMORY_ONLY
        )
        


  }

}
