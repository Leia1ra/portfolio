package spring.boot.portfolio.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.web.servlet.IServletWebExchange;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Service.PersonalService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller @RequestMapping(value = "/personal") @Slf4j
public class PersonalController {
    @Resource(name = "PersonalService")
    PersonalService service;

    @GetMapping("/")
    public String personalHome(Model model){
        List<AboutMeCollection> aboutMeList = service.aboutMeFind();
        /*System.out.println(aboutMeList.getFirst());*/
        if (aboutMeList.size() > 1){
            model.addAttribute("who",aboutMeList);
            return "Personal/AboutMeError";
        } else {
            AboutMeCollection ac;
            if (aboutMeList.isEmpty()) ac = new AboutMeCollection();
            else ac = aboutMeList.getFirst();
            model.addAttribute("who", ac);
        }

        return "Personal/Personal";
    }

    @PostMapping("/aboutMe") @ResponseBody
    public Map<String,Object> aboutMe(AboutMeCollection ac){
        System.out.println(ac.toString());
        Map<String,Object> value = new HashMap<String, Object>();
        try {
            service.aboutMeSave(ac);
            value.put("result", true);
            value.put("id", service.aboutMeFind().getFirst().getId());
        } catch (Exception e) {
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }

        return value;
    }

    @PostMapping("/introduce")
    public void introduce(HttpServletRequest req, HttpServletResponse res){
        try {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('ㅎㅇ')");
            out.println("history.back()");
            out.println("</script>");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // return "redirect:/personal/?id=hi";
    }
}
