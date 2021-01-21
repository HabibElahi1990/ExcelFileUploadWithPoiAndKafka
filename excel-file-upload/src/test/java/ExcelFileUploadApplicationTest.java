import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Habib, on 08/01/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExcelFileUploadApplication.class})
public class ExcelFileUploadApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // use WebApplicationContext for all spring beans
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    //test all of project processes
    @Test
    public void getExcelFileTest() throws Exception {

        FileInputStream file = new FileInputStream("C:\\Users\\Habib\\Documents\\My Received Files\\Test.xlsx");
        MockMultipartFile multipartFile
                = new MockMultipartFile(
                "excelToUpload",
                "Test1.xlsx",
                MediaType.TEXT_PLAIN_VALUE,
                file.readAllBytes()
        );
        mockMvc.perform(multipart("/getExcelFile")
                .file(multipartFile))
                .andExpect(status().isOk()).andReturn();

    }


}