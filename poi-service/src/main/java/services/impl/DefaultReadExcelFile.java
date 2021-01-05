package services.impl;

import exceptions.ServiceException;
import mapper.MapExcelFileCellsIntoMapCollectionParam;
import model.ExcelFile;
import model.ExcelFileRows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import services.api.ReadExcelFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DefaultReadExcelFile implements ReadExcelFile {

    private static final Log logger = LogFactory.getLog(DefaultReadExcelFile.class);

    @Override
    public List<ExcelFileRows> getRowsFromExcelFile(ExcelFile excelFile) throws ServiceException {
        List<ExcelFileRows> excelFileRowsList = new ArrayList<>();

        try {
            // get excelFile bytes
            ByteArrayInputStream bais = new ByteArrayInputStream(excelFile.getExcelFileBytes());
            Workbook workbook = new XSSFWorkbook(bais);
            Sheet dataTypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataTypeSheet.iterator();
            int i = 0;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                if (i > 0) {

                    ExcelFileRows excelFileRows = new ExcelFileRows();
                    // make an object from MapExcelFileCellsIntoMapCollectionParam for map excelFile items to Map Collection
                    MapExcelFileCellsIntoMapCollectionParam param = new MapExcelFileCellsIntoMapCollectionParam(currentRow);
                    excelFileRows.setParams(param.toMap());
                    excelFileRowsList.add(excelFileRows);
                }
                i++;
            }
        } catch (IOException e) {
            logger.error("make a error in read rows from excel");
            throw new ServiceException("make a error in read rows from excel");
        }

        return excelFileRowsList;
    }
}
