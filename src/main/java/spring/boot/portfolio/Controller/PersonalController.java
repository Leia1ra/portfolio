package spring.boot.portfolio.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Service.PersonalService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller @RequestMapping(value = "/personal")
public class PersonalController {
    @Resource(name = "PersonalService")
    PersonalService service;

    @GetMapping("/")
    public String personalHome(Model model){
        List<AboutMeCollection> aboutMeList = service.aboutMeFind();
        /*System.out.println(aboutMeList.getFirst());*/
        if (aboutMeList.size() > 1){
            model.addAttribute("who",aboutMeList);
        } else if (aboutMeList.size() == 1){
            model.addAttribute("who",aboutMeList.getFirst());
        } else {
            model.addAttribute("who", null);
        }

        return "Personal/Personal";
    }

    @PostMapping("/aboutMe") @ResponseBody
    public AboutMeCollection aboutMe(AboutMeCollection ac){
        System.out.println(ac.toString());
        // service.aboutMeSave(ac);

        return ac;
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
