package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;

@Repository
public interface AboutMeRepository extends MongoRepository<AboutMeCollection, String> {
    void deleteAllByIdNot(String id);
}
