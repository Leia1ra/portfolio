package spring.boot.portfolio.Model.AboutMeModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document("AboutMe")
public class AboutMeCollection {
    @Id
    private String id;
    private String name;        // 국문 이름
    private String end_name;    // 영문 이름
    private String phone;       // 연락처
    private List<String> email;       // 이메일
    private Map<String, String> link;   // 관련 링크(깃허브 등)
    private List<String> tag;   // 성격

    @PersistenceCreator
    public AboutMeCollection(
            String name,
            String end_name,
            String phone,
            List<String> email,
            Map<String, String> link,
            List<String> tag){
        this.name = name;
        this.end_name = end_name;
        this.phone = phone;
        this.link = link;
        this.tag = tag;
        this.email = email;
    }
}
