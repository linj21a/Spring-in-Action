/**
 * @author 60417
 * @date 2022/1/17
 * @time 16:07
 * @todo
 */
package FirthDay.Spittr.web;

import FirthDay.Spittr.Bean.Spittle;
import FirthDay.Spittr.Bean.SpittleForm;
import FirthDay.Spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"/spittles"})
public class SpittleController {

    private final SpittleRepository spittleRepository;
//    自动注入SpittleRepository
    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    /*	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model) {
		//将spittle添加到模型中
		//方式一
		//注意这里没有key，它可以根据List<Spittle> 推断 key = spittleList
		model.addAttribute(
				spittleRepository.findSpittles(
						Long.MAX_VALUE, 20));

		//方式二
		//显式指定key
		model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		//返回视图名
		return "spittles";
	}*/
    //方式三
    //它会根据请求/spittles，判断 视图为spittles
	/*@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(){
		return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
	}*/

        //设置业务处理方法，处理get请求
//    @RequestMapping(method=RequestMethod.GET)
//    public String spittles(Model model){
//        //将Spittles添加到模型中
//        System.out.println("测试 /spittles");
//        List<Spittle> spittles = spittleRepository.findSpittles(Long.MAX_VALUE, 20);
//        System.out.println(Arrays.toString(spittles.toArray()));
//        model.addAttribute("spittles",spittles);
//        System.out.println(model.toString());
//        return "spittles";//返回的视图的名字
//    }
    //这里我们需要处理带参数的get请求和不带参数的请求，使用一个方法处理
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max",defaultValue = MAX_LONG_AS_STRING )
            long max,
            @RequestParam(value = "count",defaultValue = "20")
            int count){
//        //注入一个spittleRepository实现：
//        spittleRepository = new SpittleRepositoryImp();
        return spittleRepository.findSpittles(max,count);
    }
//    通过路径参数输入，需求：应用程序需要根据给定ID展现某一个Spittle记录，该方案需要一个请求参数ID
//    //下面是通过请求参数的实现方案
//    @RequestMapping(value = "/show",method = RequestMethod.GET)
//    public String showSpittle(
//            @RequestParam("spittle_id")
//            long spittleId,
//            Model model){
//        model.addAttribute(spittleRepository.findOne(spittleId));
//        return "spittle";//返回视图名
//    }
//    使用路径变量：使用{}占位符，然后@PathVariable将value里面的值传递给spittleId
    @RequestMapping(value = "/{spittleId}",method = RequestMethod.GET)
    public String spittle(
            //        绑定路径变量：
            //@PathVariable("spittleId") //当这个变量名和value里面占位符变量值相同时则可以省略
            @PathVariable
            long spittleId,
            Model model){
        Spittle spittle = spittleRepository.findOne(spittleId);
        if(spittle==null){
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";//返回视图名
    }

    /**
     * 这里很多我们没有实现的内容，我们先简单写一个假的实现
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleForm form, Model model){
        spittleRepository.save(new Spittle(null,form.getMessage()
        ,new Date(),form.getLongitude(),form.getLatitude()));
        return "redirect:/spittles";

    }
//    编写一个方法处理抛出：DuplicateSpittleException的情况
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittleException(){
        return "error/duplicate";
    }
}
