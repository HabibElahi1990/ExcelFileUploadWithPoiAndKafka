package controller;

import dto.ExcelFileInfo;
import exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import services.ExcelFileProducerService;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class ExcelFileController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelFileController.class);

    private final ExcelFileProducerService excelFileProducerService;

    public ExcelFileController(ExcelFileProducerService excelFileProducerService) {
        this.excelFileProducerService = excelFileProducerService;
    }

    @GetMapping("/")
    public String init() {
        return "uploadFile";
    }

    /*
     * this method get excelFile that uploaded in the html form
     * get name and type
     * send in kafka producer
     */
    @PostMapping("/getExcelFile")
    public String getExcelFile(Model model, MultipartFile excelToUpload) {
        if (excelToUpload != null && excelToUpload.getOriginalFilename() != null) {
            try {
                String fileType = excelToUpload.getOriginalFilename().substring(excelToUpload.getOriginalFilename().lastIndexOf(".") + 1);
                if (!fileType.toLowerCase().equals("xlsx")) {
                    throw new ServiceException("excelToUpload's type is incorrect and it must be .xlsx");
                }
                InputStream inputStreamFile = excelToUpload.getInputStream();
                byte[] bytes = inputStreamFile.readAllBytes();
                ExcelFileInfo excelFileInfo = new ExcelFileInfo();
                excelFileInfo.setExcelFileBytes(bytes);
                excelFileInfo.setExcelFileName(excelToUpload.getOriginalFilename());
                excelFileProducerService.sendMessage(excelFileInfo);

            } catch (IOException e) {
                logger.error(e.getMessage());
                return "there are excelToUpload's body any errors in backEnd\n" + e.getMessage();
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        }
        model.addAttribute("successMessage", "getting file and send by kafka and save this file and it's items in mongoDb is successfully");
        return "uploadFile";
    }
}
