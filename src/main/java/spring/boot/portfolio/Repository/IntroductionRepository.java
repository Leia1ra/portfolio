package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;

@Repository
public interface IntroductionRepository extends MongoRepository<IntroductionCollection, String> {
}
