package spring.boot.portfolio.Model.CategoryModel.PostModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PostContent {
    ContentMode mode;
    String content;
}
