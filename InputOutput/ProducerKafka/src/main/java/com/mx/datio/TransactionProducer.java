package com.mx.datio;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TransactionProducer{
    public static void main( String[] args ) throws Exception{
    
        Options options = new Options();
    	Properties props = new Properties();
        BufferedReader br = null;
        
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
        

            String line = "";
            br = new BufferedReader(new FileReader(  cmd.getOptionValue("input")  ));
            while ((line = br.readLine()) != null) {

                ProducerRecord<String, String> record = new ProducerRecord(cmd.getOptionValue("topic"), line );
                producer.send(record);
                 Thread.sleep( Integer.parseInt( cmd.getOptionValue("sleep") ));
            }

            producer.close();


        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




    }
}
