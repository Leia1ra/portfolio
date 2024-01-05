package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.AboutMeModel.GrowthCollection;

public interface GrowthRepository extends MongoRepository<GrowthCollection, String> {
}
