/**
 * @author 60417
 * @date 2022/2/4
 * @time 17:31
 * @todo
 */
package org.yuyefanhua.pizza.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String redirectToFlow() {
        return "redirect:/pizza";
    }
}
