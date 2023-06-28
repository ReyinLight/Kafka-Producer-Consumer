package org.example;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Configuración del consumidor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("group.id", "console-consumer");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        // Crear el consumidor
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Asignar manualmente las particiones al topic
        String topic = "mi-topico";
        TopicPartition partition = new TopicPartition(topic, 0);
        consumer.assign(Collections.singletonList(partition));

        // Leer los mensajes desde el principio
        consumer.seekToBeginning(Collections.singleton(partition));

        // Leer los mensajes del topic
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Mensaje recibido: " + record.value());
            }
        }
    }
}