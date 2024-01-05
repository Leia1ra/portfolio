package spring.boot.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication @EnableMongoAuditing
public class PortfolioApplication {

    public static void main(String[] args) {
        //여기서 개발자는 공동으로 이선왕, 곽병준, 홍도완이 진행합니다.
        SpringApplication.run(PortfolioApplication.class, args);
        System.out.println("\n > Server is running on: http://localhost:8080 \n");

    }

}
