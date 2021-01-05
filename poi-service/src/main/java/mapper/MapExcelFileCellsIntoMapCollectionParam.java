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

    public Map<Integer,Object> toMap(){
        Map<Integer,Object> param=new HashMap<>();
        Iterator<Cell> cellIterator = row.iterator();
        int i=0;
        while (cellIterator.hasNext()){
            Cell currentCell = cellIterator.next();
            if (currentCell.getCellTypeEnum()== CellType.NUMERIC){
                param.put(i,currentCell.getNumericCellValue());
            }
            if (currentCell.getCellTypeEnum()== CellType.STRING){
                param.put(i,currentCell.getStringCellValue());
            }
            i++;

        }

        return param;
    }
}
