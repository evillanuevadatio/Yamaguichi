package com.mx.datio;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import org.apache.commons.cli.*;

public class TransactionProducer{
    public static void main( String[] args ) throws Exception{
    
        Options options = new Options();
    	Properties props = new Properties();
        
        Option input = new Option("i", "input", true, "input csv file transactions");
        input.setRequired(true);     options.addOption(input);
        
        Option sleepy = new Option("s", "sleep", true, "sleep mode delay");
        sleepy.setRequired(true);    options.addOption(sleepy);

        Option brockers = new Option("b", "brockers", true, "brockers list");
        brockers.setRequired(true);    options.addOption(brockers);
        
        Option topic = new Option("t", "topic", true, "topic Name");
        topic.setRequired(true);    options.addOption(topic);
        
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;


        try {
            cmd = parser.parse(options, args);
            //props.put("bootstrap.servers", "localhost:9092");
            props.put("bootstrap.servers", cmd.getOptionValue("brockers") );
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");       
 	    
            KafkaProducer<String, String> producer = new KafkaProducer(props);
        
            String msg ="12345678901234567890";

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord(cmd.getOptionValue("topic"), "msg_" + i+ "-" + msg);
                producer.send(record);
            }

            producer.close();


        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }




    }
}
