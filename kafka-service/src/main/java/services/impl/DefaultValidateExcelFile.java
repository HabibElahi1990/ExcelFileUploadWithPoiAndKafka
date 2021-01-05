package services.impl;

import dto.ExcelFileInfo;
import org.springframework.stereotype.Service;
import services.api.ValidateExcelFile;

@Service
public class DefaultValidateExcelFile implements ValidateExcelFile {

    @Override
    public boolean validateExcelFile(ExcelFileInfo excelFileInfo) {
        if (excelFileInfo==null){
            return false;
        }
        return excelFileInfo.getExcelFileBytes() != null && excelFileInfo.getExcelFileBytes().length != 0;
    }
}
