package spring.boot.portfolio.Configuration.Example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//모델
@Data @AllArgsConstructor @NoArgsConstructor
public class HomeVO {
    private String name;
    private int age;
}
