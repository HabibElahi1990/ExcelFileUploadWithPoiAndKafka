package config;

import dto.ExcelFileInfo;
import dto.ExcelFileRowsInfo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/*
in this class , determined config kafka for Consumer and due to have two model class
define two config for every class
 */
@Configuration
@EnableKafka
public class ConsumerKafkaConfig {

    public static final String EXCEL_FILE_GROUP_ID = "excel_file_group_id";
    public static final String EXCEL_FILE_ROWS_GROUP_ID = "excel_file_rows_group_id";

    @Bean
    public ConsumerFactory<String, ExcelFileInfo> consumerExcelFileInfoFactory() {

        Map<String, Object> prop = kafkaConsumerProperties();
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, EXCEL_FILE_GROUP_ID);

        return new DefaultKafkaConsumerFactory<>(prop);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExcelFileInfo> kafkaListenerExcelFileInfoContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExcelFileInfo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerExcelFileInfoFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, ExcelFileRowsInfo> consumerExcelFileRowsInfoFactory() {

        Map<String, Object> prop = kafkaConsumerProperties();
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, EXCEL_FILE_ROWS_GROUP_ID);

        return new DefaultKafkaConsumerFactory<>(prop);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExcelFileRowsInfo> kafkaListenerExcelFileRowsInfoContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExcelFileRowsInfo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerExcelFileRowsInfoFactory());
        return factory;
    }

    private Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092,localhost:9093");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return prop;
    }
}
