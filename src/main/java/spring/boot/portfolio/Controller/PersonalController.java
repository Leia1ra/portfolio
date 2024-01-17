package spring.boot.portfolio.Controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.boot.portfolio.Service.PersonalService;

@Controller @RequestMapping(value = "/personal")
public class PersonalController {
    @Resource(name = "PersonalService")
    PersonalService service;

    @GetMapping("/")
    public String personalHome(Model model){
        model.addAttribute("hello", "hello");
        return "Personal/Personal";
    }
}
