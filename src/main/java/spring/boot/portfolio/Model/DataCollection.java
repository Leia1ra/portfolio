package spring.boot.portfolio.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("data")
public class DataCollection {
    @Id
    private String name;
    private int age;
}
