package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;

public interface AboutMeRepository extends MongoRepository<AboutMeCollection, String> {

}
