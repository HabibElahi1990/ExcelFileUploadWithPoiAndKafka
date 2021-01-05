package services.api;

import model.ExcelFileRows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExcelFileRowsDao {


    void saveRowsFromExcelFIleInMongoDb(List<ExcelFileRows> excelFileRowList, String excelFileId);
}
