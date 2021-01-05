package services.producer;

import dto.ExcelFileRowsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/*
in this class and sendMessage methods
send excelFIleRows in the excel-file-rows-topic name
and get result for sending by ListenableFutureCallback class
 */

@Service
public class ExcelFileRowsProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelFileRowsProducerService.class);
    private final KafkaTemplate<String, ExcelFileRowsInfo> kafkaExcelFileRowsTemplate;

    public ExcelFileRowsProducerService(KafkaTemplate<String, ExcelFileRowsInfo> kafkaExcelFileRowsTemplate) {
        this.kafkaExcelFileRowsTemplate = kafkaExcelFileRowsTemplate;
    }

    public void sendMessage(ExcelFileRowsInfo excelFileInfo) {

        logger.info("Producing message start");
        ListenableFuture<SendResult<String, ExcelFileRowsInfo>> future = this.kafkaExcelFileRowsTemplate.send("excel-file-rows-topic", excelFileInfo);
        future.addCallback(new ListenableFutureCallback<>() {
            public void onFailure(Throwable throwable) {
                logger.info("unable send message");
            }

            public void onSuccess(SendResult<String, ExcelFileRowsInfo> stringObjectSendResult) {
                logger.info("success send message");
            }
        });
    }
}
