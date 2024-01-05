package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.CategoryModel.SkillCollection;

import java.util.Optional;

public interface SkillRepository extends MongoRepository<SkillCollection, String> {
    Optional<SkillCollection> findByName(String name);
}
