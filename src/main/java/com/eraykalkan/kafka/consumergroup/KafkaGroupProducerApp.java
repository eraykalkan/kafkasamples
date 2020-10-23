package com.eraykalkan.kafka.consumergroup;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaGroupProducerApp {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        try {
            int counter = 0;
            while (counter < 100) {
                producer.send(new ProducerRecord<String, String>("my-big-topic", "abcdefghjklmopqrstyuwxyz"));
                counter++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }

    }
}
