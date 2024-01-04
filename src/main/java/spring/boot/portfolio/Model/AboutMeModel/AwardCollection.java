package spring.boot.portfolio.Model.AboutMeModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.Date;

@Data @Document("Award")
public class AwardCollection {
    @Id
    private String id;
    private Date startDate;
    private Date endDate;
    private String location;
    private String rank;
    private Image img;

    @PersistenceCreator
    public AwardCollection(Date startDate, Date endDate, String location, String rank, Image img){
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.rank = rank;
        this.img = img;
    }
}
