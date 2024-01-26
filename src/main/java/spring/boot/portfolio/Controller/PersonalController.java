package spring.boot.portfolio.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;
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
        System.out.println("니@가 왜찍힘");
        List<AboutMeCollection> aboutMeList = service.aboutMeFind();
        if (aboutMeList.size() > 1){
            model.addAttribute("exception", true);
            model.addAttribute("aboutMeList", aboutMeList);
        } else {
            AboutMeCollection ac;
            if (aboutMeList.isEmpty()) ac = new AboutMeCollection();
            else ac = aboutMeList.getFirst();
            System.out.println(ac);
            model.addAttribute("exception", false);
            model.addAttribute("aboutMe", ac);
            model.addAttribute("introduction", service.introduceFind());
        }
        return "Personal/Personal";
    }

    @PostMapping("{id}")
    public String personalException(@PathVariable(name = "id") String id){
        System.out.println(id);
        try {
            service.aboutMeExceptionSelect(id);
            return "redirect:/personal/";
        } catch (Exception e) {
            log.info("DB DELETE ERROR Transaction -> {}",e.getMessage());
            return "redirect:/personal/?errMsg=Server Error..";
        }
    }

    @PostMapping("/aboutMe") @ResponseBody
    public Map<String,Object> aboutMe(AboutMeCollection ac){
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

    @PostMapping(value = "/introduction", params = "delete") @ResponseBody
    public Map<String, Object> introductionDelete(IntroductionCollection ic){
        Map<String,Object> value = new HashMap<>();
        try{
            service.introduceDelete(ic.getId());
            value.put("result", true);
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }
        return value;
    }
    @PostMapping(value = "/introduction", params = "save") @ResponseBody
    public Map<String, Object> introductionSave(IntroductionCollection ic){
        Map<String,Object> value = new HashMap<>();
        try{
            ic = service.introduceSave(ic);
            value.put("result", true);
            value.put("id", ic.getId());
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }
        return value;
    }
}
