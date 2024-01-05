package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;

public interface IntroductionRepository extends MongoRepository<IntroductionCollection, String> {
}
