package spring.boot.portfolio.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document("data")
public class AlarmLog {
    private Long id;
    private String title;
    private String message;
}
