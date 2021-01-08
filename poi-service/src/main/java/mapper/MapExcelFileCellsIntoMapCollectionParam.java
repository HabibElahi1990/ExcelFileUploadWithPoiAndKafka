package mapper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
get excelFile items and set into a Map collection
 */
public class MapExcelFileCellsIntoMapCollectionParam {

    private Row row;

    public MapExcelFileCellsIntoMapCollectionParam(Row row) {
        this.row = row;
    }

    public Map<String, Object> toMap(Map<Integer, String> excelHeaders) {
        Map<String, Object> param = new HashMap<>();
        Iterator<Cell> cellIterator = row.iterator();
        int i = 0;
        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                param.put(excelHeaders.get(i), currentCell.getNumericCellValue());
            }
            if (currentCell.getCellTypeEnum() == CellType.STRING) {
                param.put(excelHeaders.get(i), currentCell.getStringCellValue());
            }
            i++;

        }

        return param;
    }

    public Map<Integer, String> getExcelHeaders() {
        Map<Integer, String> headers = new HashMap<>();
        Iterator<Cell> cellIterator = row.iterator();
        int i = 0;
        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            headers.put(i, currentCell.getStringCellValue());
            i++;
        }

        return headers;
    }
}
