package com.mx.datio;


import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import org.apache.spark.api.java.JavaRDD;
import kafka.serializer.DefaultDecoder;
import org.apache.spark.streaming.Time; 

import org.apache.spark.api.java.function.Function; 
import org.apache.spark.api.java.function.Function2; 

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import scala.Tuple2;

public class KafkaStreaming{
    public static void main( String[] args ) throws Exception {
      

		/*
        Map<String, String> kafkaParams = new HashMap<String,String>();
        kafkaParams.put("metadata.broker.list", "localhost:9092");
        Set<String> topics = Collections.singleton("chartrix");
      */
        SparkConf conf = new SparkConf().setAppName("kafka-chartrix");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(2000));

       /*
        JavaPairInputDStream<String, String> directKafkaStream = KafkaUtils.createDirectStream( ssc,
                                                                                                String.class, 
                                                                                                String.class, 
                                                                                                StringDecoder.class, 
                                                                                                StringDecoder.class, 
                                                                                                kafkaParams, 
                                                                                                topics);
        directKafkaStream.foreachRDD(rdd -> {
            System.out.println("--- New RDD with " + rdd.partitions().size()  + " partitions and " + rdd.count() + " records");
                            rdd.foreach(record -> System.out.println(record._2));
                            });



        */

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "localhost:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
		kafkaParams.put("auto.offset.reset", "latest");
		kafkaParams.put("enable.auto.commit", false);
		
		//Collection<String> topics = Arrays.asList("topicA", "topicB");
		Set<String> topics = Collections.singleton("chartrix"); 

		final JavaInputDStream<ConsumerRecord<String, String>> stream =
				KafkaUtils.createDirectStream(	streamingContext,
												LocationStrategies.PreferConsistent(),
												ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
				);
			

        ssc.start();
        ssc.awaitTermination();

    }//main
}//
