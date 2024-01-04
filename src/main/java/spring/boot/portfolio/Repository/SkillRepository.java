package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.CategoryModel.SkillCollection;

public interface SkillRepository extends MongoRepository<SkillCollection, String> {
}
