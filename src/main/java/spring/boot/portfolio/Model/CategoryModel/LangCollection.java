package spring.boot.portfolio.Model.CategoryModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document("Lang")
public class LangCollection {
    @Id
    private String id;                      // 언어 번호
//    private ArrayList<String> posts_id;     // 게시글 아이디 리스트
    private String name;                    // 언어 명
    private String img;                     // 언어 아이콘
    public LangCollection(){
        this.id = "-1";
//        this.posts_id = new ArrayList<>();
        this.name = "존재하지 않는 언어";
        this.img = "";
    }
    @PersistenceCreator
    public LangCollection(String name, String img){
//        this.posts_id = posts_id;
        this.name = name;
        this.img = img;
    }
}
