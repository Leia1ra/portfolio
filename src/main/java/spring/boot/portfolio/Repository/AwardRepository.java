package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.AboutMeModel.AwardCollection;

public interface AwardRepository extends MongoRepository<AwardCollection, String> {
}
