package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.DataCollection;

public interface DataRepository extends MongoRepository<DataCollection, String> {

}
