package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.CategoryModel.CategoryCollection;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<CategoryCollection, String> {
    Optional<CategoryCollection> findByName(String name);
}
