package listener;

import config.ConsumerKafkaConfig;
import dto.ExcelFileRowsInfo;
import exceptions.ServiceException;
import model.ExcelFile;
import model.ExcelFileRows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import services.api.ExcelFileDao;
import services.api.ExcelFileRowsDao;
import services.api.ReadExcelFile;

import java.util.List;

/*
define Listener for kafka and excel-file-rows-topic for getting excelFile
 */
@Service
public class ReadRowsFromExcelFileInfoListener {

    private static final Log logger = LogFactory.getLog(ReadRowsFromExcelFileInfoListener.class);
    @Autowired
    private ReadExcelFile readExcelFile;
    @Autowired
    private ExcelFileDao excelFileDao;
    @Autowired
    private ExcelFileRowsDao excelFileRowsDao;

    /*
    containerFactory -> ascertain which factory bean for this topic
    properties -> determine type for json for getting object ExcelFileRowsInfo class
    */
    @KafkaListener(topics = "excel-file-rows-topic", groupId = ConsumerKafkaConfig.EXCEL_FILE_ROWS_GROUP_ID
            , containerFactory = "kafkaListenerExcelFileRowsInfoContainerFactory"
            , properties = {"spring.json.value.default.type:dto.ExcelFileRowsInfo"})
    public void getInsuredExcelFileInfo(ExcelFileRowsInfo excelFileRowsInfo) {

        try {
            if (excelFileRowsInfo.getExcelFileInfoId() != null) {
                logger.info(" I get your Message");
                ExcelFile excelFileById = excelFileDao.getExcelFileById(excelFileRowsInfo.getExcelFileInfoId());
                if (excelFileById == null) {
                    logger.error("there is not excel file with id ->" + excelFileRowsInfo.getExcelFileInfoId());
                    return;
                }
                List<ExcelFileRows> excelFileRowList = readExcelFile.getRowsFromExcelFile(excelFileById);
                if (excelFileRowList == null || excelFileRowList.isEmpty()) {
                    logger.error("there are not any row in these excel");
                    return;
                }
                excelFileRowsDao.saveRowsFromExcelFIleInMongoDb(excelFileRowList, excelFileById.getId());
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
    }
}
