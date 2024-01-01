package spring.boot.portfolio.Configuration.Example;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {
    @RequestMapping(value = "/homeAction", method = RequestMethod.GET)
    public String homeAction(HttpServletRequest req){
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String ip = req.getRemoteHost();
        System.out.println(ip);
        System.out.println("Get : 이름 : "+ name + ", 나이 : " + age);
        return "redirect:/";
    }

    /*@RequestMapping(value = "/homeAction", method = RequestMethod.GET)
    public String homeAction(String name, int age){
        System.out.println("Get : 이름 : "+ name + ", 나이 : " + age);
        return "redirect:/";
    }*/

    /*@RequestMapping(value = "/homeAction", method = RequestMethod.POST)
    public String homeAction(HomeVO vo){
        System.out.println("Post : 이름 : "+ vo.getName() + ", 나이 : " + vo.getAge());

        return "redirect:/";
    }*/

    /*@GetMapping("/homeAction")
    public String homeAction(@RequestParam(value = "name", required = false) String name, int age){
        *//*if(name==null){
            name = "";
        }*//*
        System.out.println("이름 : " + name + ", 나이 : " + age);

        return "redirect:/";
    }*/

}
