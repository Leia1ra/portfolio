package spring.boot.portfolio.Configuration.Example;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @Slf4j @RequestMapping("/community")
public class CommunityController {
    @Resource(name = "community")
    CommunityService service;

    @RequestMapping("/")
    public String community(Model model){
        List<Community> list = service.communityList();
        model.addAttribute("list",list);
        return "example/community/home";
    }

    /*글 작성*/
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(@RequestParam(name = "id", required = false) String id, Model model){
        if(id != null){
            Community c = new Community();
            c.setId(id);
            model.addAttribute("detail", service.communityView(c));
            return "redirect:/community/view?="+id;
        } else {
            return "example/community/write";
        }
    }
    @PostMapping("/writeAction")
    public String writeAction(Community community){
        System.out.print(community.toString());
        service.communityWrite(community);
        return "redirect:/community/";
    }

    @GetMapping("/view")
    public String view(Community community, Model model){
        log.info("no -> {}", community.getId());
        community = service.communityView(community);
        log.info("community -> {}", community);

        model.addAttribute("detail",community);
        return "example/community/view";
    }

}
