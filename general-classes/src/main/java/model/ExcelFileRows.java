package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class ExcelFileRows {

    @Id
    private String id;
    private String excelFileId;
    private Map<String,Object> params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExcelFileId() {
        return excelFileId;
    }

    public void setExcelFileId(String excelFileId) {
        this.excelFileId = excelFileId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ExcelFileRows{" +
                "id='" + id + '\'' +
                ", excelFileId='" + excelFileId + '\'' +
                ", params=" + params +
                '}';
    }
}
