package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.CategoryModel.LangCollection;

import java.util.Optional;

public interface LangRepository extends MongoRepository<LangCollection, String> {
    Optional<LangCollection> findByName(String name);
}
