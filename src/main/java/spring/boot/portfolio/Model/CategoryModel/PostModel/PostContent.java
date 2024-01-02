package spring.boot.portfolio.Model.CategoryModel.PostModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostContent {
    ContentMode mode;
    String content;
}
