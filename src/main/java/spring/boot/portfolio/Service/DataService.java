package spring.boot.portfolio.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.DataCollection;
import spring.boot.portfolio.Repository.DataRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class DataService {
    // private final MongoTemplate mongoTemplate;
    private final DataRepository dataRepository;
    MongoTemplate mt;

    public List<DataCollection> test(){
        DataCollection d = new DataCollection();
        d.setName("곽병준");
        d.setAge(28);

        // dataRepository.insert(d);
        dataRepository.save(d);
        return dataRepository.findAll();
    }

}
