import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SuppressWarnings("SpringBootApplicationSetup")
@SpringBootApplication
@ComponentScan({"config", "services","controller","model","listener"})
@EnableMongoRepositories(basePackages = "repository")
public class ExcelFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelFileUploadApplication.class, args);
    }
}
