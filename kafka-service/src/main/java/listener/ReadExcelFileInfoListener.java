package listener;

import config.ConsumerKafkaConfig;
import dto.ExcelFileInfo;
import dto.ExcelFileRowsInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import services.api.ExcelFileDao;
import services.api.ValidateExcelFile;
import services.producer.ExcelFileRowsProducerService;


/*
define Listener for kafka and excel-file-topic for getting excelFile
 */
@Service
public class ReadExcelFileInfoListener {

    private static final Log logger = LogFactory.getLog(ReadExcelFileInfoListener.class);
    @Autowired
    private ValidateExcelFile validateExcelFile;
    @Autowired
    private ExcelFileDao excelFileDao;

    @Autowired
    private ExcelFileRowsProducerService excelFileRowsProducerService;

    /*
    containerFactory -> ascertain which factory bean for this topic
    properties -> determine type for json for getting object ExcelFileInfo class
     */
    @KafkaListener(topics = "excel-file-topic", groupId = ConsumerKafkaConfig.EXCEL_FILE_GROUP_ID
            , containerFactory = "kafkaListenerExcelFileInfoContainerFactory"
            , properties = {"spring.json.value.default.type:dto.ExcelFileInfo"})
    public void getInsuredExcelFileInfo(ExcelFileInfo excelFileInfo) {

        if (excelFileInfo.getExcelFileBytes() != null) {
            logger.info(" I get your Message");
            if (validateExcelFile.validateExcelFile(excelFileInfo)) {
                String excelFileId = excelFileDao.saveExcelFileInMongoDb(excelFileInfo);
                ExcelFileRowsInfo excelFileRowsInfo = new ExcelFileRowsInfo();
                excelFileRowsInfo.setExcelFileInfoId(excelFileId);
                excelFileRowsProducerService.sendMessage(excelFileRowsInfo);
            }
        }
    }
}
