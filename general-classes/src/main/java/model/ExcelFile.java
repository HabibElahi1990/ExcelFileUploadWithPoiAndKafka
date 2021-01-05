package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ExcelFile {
    @Id
    private String id;
    private String excelFileName;
    private byte[] excelFileBytes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
