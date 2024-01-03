package spring.boot.portfolio.Model.CategoryModel.PostModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data @Document("PostData") @NoArgsConstructor
public class PostCollection {
    @Id
    private String id;                    // 게시글 번호
    private String name;                // 게시글 이름
    private ArrayList<PostContent> contents; // 게시글 내용
//    private ArrayList<String> comments_id;     // 댓글들 id
    private String category;            // 게시글 id
    @CreatedDate
    private Date write_day;             // 작성일
    @LastModifiedDate
    private Date update_day;            // 수정일

    @PersistenceCreator
    public PostCollection(String name, ArrayList<PostContent> contents, String category){
        this.name = name;
        this.contents = contents;
//        this.comments_id = new ArrayList<>();
        this.category = category;
    }

    static public PostCollection emptyData(){
        PostCollection result = new PostCollection();
        result.id = "-1";
        result.name = "비어있는 게시글";
        result.category = "empty";
        result.contents = new ArrayList<>();
//        result.comments_id = new ArrayList<>();
        result.write_day = new Date();
        return result;
    }
}

