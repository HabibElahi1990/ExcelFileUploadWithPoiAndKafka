package services.api;

import dto.ExcelFileInfo;
import model.ExcelFile;
import model.ExcelFileRows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExcelFileDao {

    String saveExcelFileInMongoDb(ExcelFileInfo excelFileInfo);

    ExcelFile getExcelFileById(String excelFileId);

}
