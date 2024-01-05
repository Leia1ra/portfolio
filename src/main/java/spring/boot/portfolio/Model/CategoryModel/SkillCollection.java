package spring.boot.portfolio.Model.CategoryModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Skill")
public class SkillCollection {
    @Id
    private String id;
    private String name;           // 스킬 이름
    private String description;     // 스킬 설명
    private int level;              // 스킬 실력 (레벨 단위)

    @PersistenceCreator
    public SkillCollection(String name, String description, int level){
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public SkillCollection() {
        this.id = "-1";
        this.name = "존재하지 않는 기술";
        this.description = "";
        this.level = -1;
    }
}
