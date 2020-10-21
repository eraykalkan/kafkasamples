package com.eraykalkan.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerSubscribeApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer consumer = new KafkaConsumer(props);

        ArrayList<String> topics = new ArrayList<String>();
        topics.add("my-topic");
        topics.add("my-other-topic");

        consumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> consumerRecord : records) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key:%s, Value: %s",
                            consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(),
                            consumerRecord.value()
                    ));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.close();
        }

    }
}
