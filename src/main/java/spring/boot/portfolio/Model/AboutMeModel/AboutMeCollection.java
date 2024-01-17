package spring.boot.portfolio.Model.AboutMeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor
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
}
