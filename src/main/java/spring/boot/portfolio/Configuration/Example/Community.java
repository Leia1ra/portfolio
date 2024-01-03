package spring.boot.portfolio.Configuration.Example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Calendar;

@Document(collection = "Community")
@Data @NoArgsConstructor @AllArgsConstructor
public class Community {
    @Id
    private String id;

    @Field(name = "title")
    private String title;
    @Field(name = "content")
    private String content;

    @CreatedDate @Field(name = "writedate")
    private String writedate;
    @LastModifiedDate @Field(name = "lastUpdate")
    private String lastUpdate;

    @Transient
    private Boolean isNull = false;


}
