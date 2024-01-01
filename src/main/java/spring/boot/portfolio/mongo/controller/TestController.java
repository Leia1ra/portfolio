package spring.boot.portfolio.mongo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.portfolio.mongo.service.TestService;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestService testService;

    @GetMapping("/")
    public String test() {
        testService.mongoInsert();
        return "test";
    }
}
