package dto;

import java.io.Serializable;

public class ExcelFileInfo implements Serializable {

    private String excelFileName;
    private byte[] excelFileBytes;

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public byte[] getExcelFileBytes() {
        return excelFileBytes;
    }

    public void setExcelFileBytes(byte[] excelFileBytes) {
        this.excelFileBytes = excelFileBytes;
    }
}
