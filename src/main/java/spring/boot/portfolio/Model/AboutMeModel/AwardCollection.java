package spring.boot.portfolio.Model.AboutMeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Document("Award")
public class AwardCollection {
    @Id
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String title;
    private String location;
    private String rank;
}
