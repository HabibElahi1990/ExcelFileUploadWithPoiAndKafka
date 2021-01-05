package repository;

import model.ExcelFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExcelFileRepository extends MongoRepository<ExcelFile,String> {

}
