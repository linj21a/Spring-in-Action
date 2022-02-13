/**
 * @author 60417
 * @date 2022/1/17
 * @time 16:07
 * @todo
 */
package FirthDay.Spittr.web;

import FirthDay.Spittr.Bean.Spitter;
import FirthDay.Spittr.Bean.SpitterForm;
import FirthDay.Spittr.data.SpitterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = {"/spitter"})
public class SpitterController {
    private final SpitterRepository spitterRepository;
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    //    展现一个表单，允许用户注册该应用：用户输入register，可以进入注册表单页面
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegisterForm(Model model){
        //添加spitterForm对象到model属性里面
        if(!model.containsAttribute("spitterForm")){
            model.addAttribute(new SpitterForm());
        }
        System.out.println("text");
        return "registerForm";//返回对应的视图
    }

    //编写一个方法，用于处理注册表单提交的数据
    //现在使用java validation api，需要在控制器开启校验
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistration(
            //@Valid用来校验输入，errors保存检验的结果，我们需要利用这个结果来进行校验，否则校验没有用
            @Valid SpitterForm spitterForm,
            Errors errors
            ,RedirectAttributes model
            ,HttpServletRequest request
    ) throws IOException {
        if (errors.hasErrors()) {
            System.out.println("校验参数--不通过的参数");
            System.out.println(spitterForm.toString());
            return "registerForm";
        }
        System.out.println("校验参数--通过的参数");
        System.out.println(spitterForm.toString());
        Spitter spitter = spitterForm.toSpitter();
        spitterRepository.save(spitter);
        MultipartFile profilePicture = spitterForm.getProfilePicture();
        if(!profilePicture.isEmpty()){
            String temp_path = request.getSession().getServletContext().getRealPath("/")+"tmp/spittr";
            File file1 = new File(temp_path);//目录不存在则创建
            if(!file1.exists()){
                file1.mkdirs();
            }
            File file = new File( temp_path+ "/"+spitter.getUsername() + ".jpg");
            System.out.println("6: "+file.getAbsolutePath());//测试文件目录
            while (!file.exists()){
                file.createNewFile();
            }
            profilePicture.transferTo(file);
            System.out.println("图片上传成功！");
        }else
            System.out.println("上传失败，文件为空！");
        spitterRepository.addSpitter(spitter);
        model.addFlashAttribute("spitter",spitter);//将这个spitter放到flash属性里面
        model.addAttribute("username",spitter.getUsername());
        return "redirect:/spitter/{username}";//重定向到基本信息页面。
    }
    //添加一个方法用于展示个人用户的基本信息：
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(
            @PathVariable
            String username, Model model
            ){
        if(!model.containsAttribute("spitter")){//模型里面没有
            //从数据库里面查找保存：
            Spitter spitter = spitterRepository.findSpitterByName(username);
            model.addAttribute(spitter);//默认的key是spitter,<"spitter",spitter>
        }
        //模型里面有，可以直接返回到profile身份信息里面
        return "profile";//返回视图的名字
    }
    @RequestMapping(value="/me", method=RequestMethod.GET)
    public String me() {
        System.out.println("ME ME ME ME ME ME ME ME ME ME ME");
        return "home";
    }
}
