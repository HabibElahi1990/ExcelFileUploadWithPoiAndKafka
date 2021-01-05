package services.api;

import dto.ExcelFileInfo;
import exceptions.ServiceException;
import model.ExcelFile;
import model.ExcelFileRows;

import java.util.List;

public interface ReadExcelFile {

    default List<ExcelFileRows> getRowsFromExcelFile(ExcelFile excelFile) throws ServiceException {
        return null;
    }
}
