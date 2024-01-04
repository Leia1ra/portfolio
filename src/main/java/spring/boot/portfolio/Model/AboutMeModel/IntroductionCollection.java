package spring.boot.portfolio.Model.AboutMeModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Introduction")
public class IntroductionCollection {
    @Id
    private String id;
    private String field;
    private String content;

    @PersistenceCreator
    public IntroductionCollection(String field, String content){
        this.field = field;
        this.content = content;
    }
}
