package spring.boot.portfolio.Model.CategoryModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data @Document("Category")
public class CategoryCollection {
    @Id
    private String id;                      // 카테고리 번호
    private ArrayList<String> posts_id;     // 게시글 아이디 리스트
    private String name;                    // 카테고리 명
}
