/**
 * @author 60417
 * @date 2022/1/16
 * @time 19:40
 * @todo
 */
package FirthDay.Spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//可以设置多个url映射拦截
@RequestMapping(value = {"/","/homepage"})

public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "home";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String homeRegister(){
        return "register";
    }
}
