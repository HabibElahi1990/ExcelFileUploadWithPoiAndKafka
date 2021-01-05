package services.impl;

import dto.ExcelFileInfo;
import model.ExcelFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ExcelFileRepository;
import services.api.ExcelFileDao;

@Service
public class DefaultExcelFileDao implements ExcelFileDao {

    @Autowired
    private ExcelFileRepository excelFileRepository;

    @Override
    public String saveExcelFileInMongoDb(ExcelFileInfo excelFileInfo) {
        ExcelFile excelFile = new ExcelFile();
        excelFile.setExcelFileBytes(excelFileInfo.getExcelFileBytes());
        excelFile.setExcelFileName(excelFileInfo.getExcelFileName());
        ExcelFile insertedExcelFile = excelFileRepository.save(excelFile);
        return insertedExcelFile.getId();
    }

    @Override
    public ExcelFile getExcelFileById(String excelFileId) {
        return excelFileRepository.findById(excelFileId).orElse(null);

    }
}
