package spring.boot.portfolio.Model.AboutMeModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data @Document("Award")
public class AwardCollection {
    @Id
    private String id;
    private Date startDate;
    private Date endDate;
    private String location;
    private String rank;
    private String img;         // DB에 이미지는 지원되지 않는 형식이라 일단 String으로 함, 바이너리로 처리하던 Base64로 처리하던 링크로 처리하던 해야할듯?

    @PersistenceCreator
    public AwardCollection(Date startDate, Date endDate, String location, String rank, String img){
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.rank = rank;
        this.img = img;
    }
}
