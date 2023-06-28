package org.example;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;



public class KafkaProducerExample {
    public static void main(String[] args) {
        // Configuración del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        // Crear el productor
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Enviar mensajes al topic
        String topic = "mi-topico";
        String message = "Tanga nana";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.out.println("Error al enviar el mensaje: " + exception.getMessage());
                } else {
                    System.out.println("Mensaje enviado correctamente. Offset: " + metadata.offset());
                }
            }
        });

        // Cerrar el productor
        producer.close();
    }
}