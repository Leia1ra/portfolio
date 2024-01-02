package spring.boot.portfolio.Model.CategoryModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data @Document("Category")
public class CategoryCollection {
    @Id
    private String id;                      // 카테고리 번호
    private ArrayList<String> posts_id;     // 게시글 아이디 리스트
    private String name;                    // 카테고리 명

    public CategoryCollection(){
        this.id = "-1";
        this.posts_id = new ArrayList<>();
        this.name = "존재하지 않는 카테고리";
    }
    @PersistenceCreator
    public CategoryCollection(ArrayList<String> posts_id, String name){
        this.posts_id = posts_id;
        this.name = name;
    }
}
