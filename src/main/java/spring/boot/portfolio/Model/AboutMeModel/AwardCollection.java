package spring.boot.portfolio.Model.AboutMeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Document("Award")
public class AwardCollection {
    @Id
    private String id;
    private Date start_date;
    private Date end_date;
    private String location;
    private String rank;
    private String img; // DB에 이미지는 지원되지 않는 형식이라 일단 String으로 함, 바이너리로 처리하던 Base64로 처리하던 링크로 처리하던 해야할듯?
}
