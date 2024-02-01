package spring.boot.portfolio.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Model.AboutMeModel.AwardCollection;
import spring.boot.portfolio.Model.AboutMeModel.GrowthCollection;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;
import spring.boot.portfolio.Service.PersonalService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
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
        if (aboutMeList.size() > 1){
            model.addAttribute("exception", true);
            model.addAttribute("aboutMeList", aboutMeList);
        } else {
            AboutMeCollection ac;
            MultipartFile file;
            if (aboutMeList.isEmpty()) ac = new AboutMeCollection();
            else ac = aboutMeList.getFirst();

            model.addAttribute("exception", false);
            model.addAttribute("aboutMe", ac);
            model.addAttribute("introduction", service.introduceFind());
            model.addAttribute("growth", service.growthFind());
            model.addAttribute("award", service.awardFind());
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
    public Map<String,Object> aboutMe(AboutMeCollection ac, @RequestParam(name = "imgFile", required = false)MultipartFile file){
        Map<String,Object> value = new HashMap<String, Object>();
        try {
            if(file != null){
                System.out.println("file null 아님");
                // 이미지를 바이트 배열로 변환
                byte[] imageBytes = file.getBytes();
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
                System.out.println(ext);
                String base64Image = "data:image/" + ext + ";base64," + Base64.getEncoder().encodeToString(imageBytes);
                ac.setImg(base64Image);
            } else {
                System.out.println("file null임");
                List<AboutMeCollection> a = service.aboutMeFind();
                ac.setImg(a.getFirst().getImg());
            }

            service.aboutMeSave(ac);
            value.put("result", true);
            value.put("id", service.aboutMeFind().getFirst().getId());
        } catch (Exception e) {
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

    @PostMapping(value = "/growth", params = "save") @ResponseBody
    public Map<String, Object> growthSave(GrowthCollection gc){
        System.out.println(gc);
        Map<String, Object> value = new HashMap<>();
        try{
            gc = service.growthSave(gc);
            value.put("id", gc.getId());
            value.put("result", true);
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }

        return value;
    }
    @PostMapping(value = "/growth", params = "delete") @ResponseBody
    public Map<String, Object> growthDelete(GrowthCollection gc){
        Map<String, Object> value = new HashMap<>();
        try{
            service.growthDelete(gc.getId());
            value.put("result", true);
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }
        return value;
    }



    @PostMapping(value = "/award", params = "save") @ResponseBody
    public Map<String, Object> awardSave(AwardCollection ac, String date){
        Map<String, Object> value = new HashMap<>();
        try{
            ac = service.awardSave(ac);
            value.put("id", ac.getId());
            value.put("result", true);
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }
        return value;
    }
    @PostMapping(value = "/award", params = "delete") @ResponseBody
    public Map<String, Object> awardDelete(AwardCollection ac){
        System.out.println(ac);
        Map<String, Object> value = new HashMap<>();
        try{
            service.awardDelete(ac.getId());
            value.put("result", true);
        } catch (Exception e){
            log.info("DB Error Transaction -> {}", e.getMessage());
            value.put("result", false);
        }

        return value;
    }
}
