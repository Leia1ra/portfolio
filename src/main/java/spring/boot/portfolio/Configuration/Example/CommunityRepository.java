package spring.boot.portfolio.Configuration.Example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommunityRepository extends MongoRepository<Community, String> {

}

