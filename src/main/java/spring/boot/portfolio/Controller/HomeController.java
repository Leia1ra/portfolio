package spring.boot.portfolio.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.portfolio.Service.DataService;

@Controller
public class HomeController {
    @Autowired
    DataService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();

        /*List<DataCollection> list = service.test();
        for(DataCollection d : list){
            System.out.println(d.toString());
        }*/

        mav.addObject("msg","hello");
        mav.setViewName("home");
        return mav;
    }

}
