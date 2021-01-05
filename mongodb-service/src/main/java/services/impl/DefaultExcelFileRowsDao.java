package services.impl;

import model.ExcelFileRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ExcelFileRowsRepository;
import services.api.ExcelFileRowsDao;

import java.util.List;

@Service
public class DefaultExcelFileRowsDao implements ExcelFileRowsDao {

    @Autowired
    private ExcelFileRowsRepository excelFileRowsRepository;

    @Override
    public void saveRowsFromExcelFIleInMongoDb(List<ExcelFileRows> excelFileRowList, String excelFileId) {
        excelFileRowList.forEach(n -> {
            n.setExcelFileId(excelFileId);
            excelFileRowsRepository.save(n);
        });
    }
}
