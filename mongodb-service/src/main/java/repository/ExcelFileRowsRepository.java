package repository;

import model.ExcelFileRows;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExcelFileRowsRepository extends MongoRepository<ExcelFileRows, String> {

}
