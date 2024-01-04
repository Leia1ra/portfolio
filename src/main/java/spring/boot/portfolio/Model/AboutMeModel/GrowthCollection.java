package spring.boot.portfolio.Model.AboutMeModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data @Document("Growth")
public class GrowthCollection {
    @Id
    private String id;
    private Date startDate;
    private Date endDate;
    private String location;
    private String details;
    private String etc;

    @PersistenceCreator
    public GrowthCollection(Date startDate, Date endDate, String location, String details, String etc){
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.details = details;
        this.etc = etc;
    }
}
