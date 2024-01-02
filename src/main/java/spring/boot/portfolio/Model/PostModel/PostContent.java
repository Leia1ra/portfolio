package spring.boot.portfolio.Model.PostModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostContent {
    ContentMode mode;
    String content;
}
