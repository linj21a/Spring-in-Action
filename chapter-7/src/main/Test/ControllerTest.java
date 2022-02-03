/**
 * @author 60417
 * @date 2022/1/16
 * @time 20:36
 * @todo
 */

import FirthDay.Spittr.Bean.Spitter;
import FirthDay.Spittr.Bean.Spittle;
import FirthDay.Spittr.data.SpitterRepository;
import FirthDay.Spittr.data.SpittleRepository;
import FirthDay.Spittr.web.HomeController;
import FirthDay.Spittr.web.SpitterController;
import FirthDay.Spittr.web.SpittleController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ControllerTest {
    //简单的POJO类方法的测试
    @Test
    public void TestController(){
        //没有从Spring MVC控制器的角度进行测试
        HomeController homeController = new HomeController();
        assertEquals("home",homeController.home());
        assertEquals("register",homeController.homeRegister());
    }
    //Spring Mvc角度
    @Test
    public void TestControllerPage() throws Exception {
        //standaloneSetup、get、view均为：MockMvc测试构建类MockMvcBuilders的静态方法
        //org.springframework.test.web.servlet.setup.MockMvcBuilders
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();
        //perform可能抛出异常
        //get方法测试
        mockMvc.perform(get("/")).
                andExpect(view().name("home"));
        //post方法测试
        mockMvc.perform(post("/")).
                andExpect(view().name("register"));

    }
    @Test
    public void shoudShowPagedSpittles()throws Exception {//测试分页Spittle列表的新方法
        List<Spittle> spittleList = createSpittleList(50);
        //创建mock 仓库
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        //when thenReturn
        when(mockRepository.findSpittles(238900,50)
                ).thenReturn(spittleList);//预期的max和count
        //创建对应的控制器：注入创建的spittleList
        SpittleController controller = new SpittleController(mockRepository);
        //创建mockMVC进行测试
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(
                new InternalResourceView("/WEB-INF/views/spittles.jsp")
        ).build();
        //发起get测试：
        mockMvc.perform(get("/spittles?max=238900&count=50"))//这里通过在url里面传递参数进行判断
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittles"))
                .andExpect(model().attribute("spittles",hasItems(spittleList.toArray())));
    }
    @Test
    public void testSpittle()throws Exception {//测试Spittle请求路径变量中指定
        Spittle spittle = new Spittle("hello",new Date());
        //创建mock 仓库
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        //when thenReturn
        when(mockRepository.findOne(12345)
        ).thenReturn(spittle);//预期的max和count
        //创建对应的控制器：注入创建的spittleList
        SpittleController controller = new SpittleController(mockRepository);
        //创建mockMVC进行测试
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(
                new InternalResourceView("/WEB-INF/views/spittles.jsp")
        ).build();
        //发起get测试：
        mockMvc.perform(get("/spittles/12345"))//这里通过在url里面的路径变量
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle",spittle));
    }
//    SpitterController 测试注册表单展示的方法 registerForm
    @Test
    public void shouldShowRegistrationForm() throws Exception {
        SpitterController controller = new SpitterController(null);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }
    //测试处理表单的控制器方法：
    @Test
    public void shouldProcessRegisterForm() throws Exception {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("Jack", "Bauer", "jbauer",
                "24hours","");
        Spitter saved = new Spitter(24L, "Jack","Jack", "Bauer", "jbauer", "24hours");
        when(mockRepository.save(unsaved)).thenReturn(saved);

        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        //发起post请求
        mockMvc.perform(post("/spitter/register")
                        .param("firstName", "Jack")
                        .param("lastName", "Bauer")
                //用户名
                        .param("username", "jbauer")
                        .param("password", "24hours"))
                //判断是否重定向了 处理post请求时，请求处理完成后一般进行一下重定向，
                // 这样浏览器点击刷新就不会重复提交表单了。
                //spitter/jbauer为新建用户的基本信息页
                .andExpect(redirectedUrl("/spitter/jbauer"));

        //校验SpittleRepository的mock实现是否真正用来保存表单上传入的数据
        verify(mockRepository, atLeastOnce());
    //.save(unsaved);--初衷是保证至少调用一次save方法
    }
    @Test
    public void SpittleControllerTest_shouldShowRecentSpittles() throws Exception {
//        创建20个spittle
        List<Spittle> expectedSpittles = createSpittleList(20);
//        使用mock 创建一个repository
        //import static org.mockito.Mockito.mock;
        //import static org.mockito.Mockito.when;
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittles(Long.MAX_VALUE, 20))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();//设置控制器 Mock Spring MVC
//        mock 发起get请求：
        //import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
        //--MockMvcBuilders.standaloneSetup;
        //import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
        //--MockMvcResultMatchers.view;
        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList",
                        hasItems(expectedSpittles.toArray())));
        //org.junit.matchers.JUnitMatchers.hasItems;

    }
    private List<Spittle> createSpittleList(int count){
        List<Spittle> spittles = new ArrayList<>();
        for(int i=0;i<count;i++){
            spittles.add(new Spittle("Spittle "+i,new Date()));
        }
        return spittles;
    }
}
