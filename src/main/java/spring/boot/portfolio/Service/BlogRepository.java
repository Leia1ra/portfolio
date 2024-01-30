package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.CategoryModel.PostModel.BlogCollection;

import java.util.Optional;

public interface BlogRepository extends MongoRepository<BlogCollection, String> {
    Optional<BlogCollection> findByName(String name);
}
