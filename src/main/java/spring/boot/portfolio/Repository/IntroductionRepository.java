package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;

import java.util.List;

@Repository
public interface IntroductionRepository extends MongoRepository<IntroductionCollection, String> {
    List<IntroductionCollection> findAllByOrderByNoAsc();
}
