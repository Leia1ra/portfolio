package spring.boot.portfolio.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.mongo.document.AlarmLog;

@RequiredArgsConstructor
@Service
public class TestService {
    private final MongoTemplate mongoTemplate;

    public void mongoInsert(){
        AlarmLog alarmLog = new AlarmLog(1L, "Title", "Message");
        mongoTemplate.insert(alarmLog);
    }
}
