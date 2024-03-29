package spring.boot.portfolio.Model.CategoryModel.PostModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;


@Data @Document("BlogData") @NoArgsConstructor
public class BlogCollection {
    @Id
    private String id;                    // 게시글 번호
    private String name;                // 게시글 이름
    private String thumbnail; //썸네일
    private String contents; //게시글 내용
//    private List<BlogContent> contents; // 게시글 내용
//    private ArrayList<String> comments_id;     // 댓글들 id
    private List<String> lang_id;           // 게시글 id
    private List<String> skill_id;           // 관련스킬 id 제안: 어떤 프로젝트냐에 따라서 관련된 스킬이 여러개일 수도 있으니 이걸 리스트로 하는건 어떰?
    @CreatedDate
    private Date write_day;             // 작성일
    @LastModifiedDate
    private Date update_day;            // 수정일
    @PersistenceCreator
    public BlogCollection(String name, String thumbnail, /*List<BlogContent>*/String contents, List<String> lang_id, List<String> skill_id){
        this.name = name;
        this.thumbnail = thumbnail;
        this.contents = contents;
//        this.comments_id = new ArrayList<>();
        this.lang_id = lang_id;
        this.skill_id = skill_id;
    }

    static public BlogCollection emptyData(){
        BlogCollection result = new BlogCollection();
        result.id = "-1";
        result.name = "비어있는 게시글";
        result.lang_id = new ArrayList<>();
        result.skill_id = new ArrayList<>();
//        result.contents = new ArrayList<>();
        result.contents = "";
//        result.comments_id = new ArrayList<>();
        result.write_day = new Date();
        return result;
    }
}

