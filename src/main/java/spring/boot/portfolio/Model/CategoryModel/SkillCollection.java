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
    private String skill;           // 스킬 이름
    private String description;     // 스킬 설명
    private int level;              // 스킬 실력 (레벨 단위)

    @PersistenceCreator
    public SkillCollection(String skill, String description, int level){
        this.skill = skill;
        this.description = description;
        this.level = level;
    }
}
