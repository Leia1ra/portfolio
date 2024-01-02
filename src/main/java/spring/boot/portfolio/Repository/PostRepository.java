package spring.boot.portfolio.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.portfolio.Model.PostModel.PostCollection;

import java.util.Optional;

public interface PostRepository extends MongoRepository<PostCollection, String> {
    Optional<PostCollection> findByName(String name);
}
