package services;


import dto.ExcelFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/*
in this class and sendMessage methods
send excelFIle in the excel-file-topic name
and get result for sending by ListenableFutureCallback class
 */
@Service
public class ExcelFileProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelFileProducerService.class);
    private final KafkaTemplate<String, ExcelFileInfo> kafkaExcelFileTemplate;

    public ExcelFileProducerService(KafkaTemplate<String, ExcelFileInfo> kafkaExcelFileTemplate) {
        this.kafkaExcelFileTemplate = kafkaExcelFileTemplate;
    }

    public void sendMessage(ExcelFileInfo excelFileInfo) {

        logger.info("Producing message start");
        ListenableFuture<SendResult<String, ExcelFileInfo>> future = this.kafkaExcelFileTemplate.send("excel-file-topic", excelFileInfo);
        future.addCallback(new ListenableFutureCallback<>() {
            public void onFailure(Throwable throwable) {
                logger.info("unable send message");
            }

            public void onSuccess(SendResult<String, ExcelFileInfo> stringObjectSendResult) {
                logger.info("success send message");
            }
        });
    }
}
