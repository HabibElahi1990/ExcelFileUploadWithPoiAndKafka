package config;

import dto.ExcelFileInfo;
import dto.ExcelFileRowsInfo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/*
in this class , determined config kafka for Producer and due to have two model class
define two config for every class
 */
@Configuration
@EnableKafka
public class ProducerKafkaConfig {

    @Bean
    public ProducerFactory<String, ExcelFileInfo> producerExcelFileFactory() {

        return new DefaultKafkaProducerFactory<>(kafkaProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, ExcelFileInfo> kafkaExcelFileTemplate() {
        return new KafkaTemplate<>(producerExcelFileFactory());
    }

    @Bean
    public ProducerFactory<String, ExcelFileRowsInfo> producerExcelFileRowsFactory() {

        return new DefaultKafkaProducerFactory<>(kafkaProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, ExcelFileRowsInfo> kafkaExcelFileRowsTemplate() {
        return new KafkaTemplate<>(producerExcelFileRowsFactory());
    }


    private Map<String, Object> kafkaProducerProperties() {
        Map<String, Object> prop = new HashMap<>();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092,localhost:9093");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        return prop;
    }


}
