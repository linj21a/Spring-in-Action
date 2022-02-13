/**
 * @author 60417
 * @date 2022/1/16
 * @time 17:00
 * @todo
 */
package FirthDay.Spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.io.IOException;

//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//组件扫描的包位置
@ComponentScan(basePackages = {"FirthDay.Spittr.web"})
//启用Spring MVC
@EnableWebMvc
//extends WebMvcConfigurerAdapter 过时，推荐使用：implement
public class WebConfig implements WebMvcConfigurer {

    //静态资源的处理，
    // DefaultServletHandlerConfigurer的enable方法将交给Servlet容器的默认的Servlet处理
    //而不是DispatcherServlet本身来处理
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //    //配置视图解析器
//    @Bean
//    public ViewResolver viewResolver1(){
//        InternalResourceViewResolver resolver =
//                new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");//设置视图的前缀
//        resolver.setSuffix(".jsp");//设置后缀
//        resolver.setExposeContextBeansAsAttributes(true);//
//        return resolver;
//    }
//    //配置Tiles配置器
//    //我们把它添加到WebConfig里面：
//    @Bean
//    public TilesConfigurer tilesConfigurer(){
//        TilesConfigurer tiles = new TilesConfigurer();
//        //指定Tile定义的位置：
//        tiles.setDefinitions(new String[]{
//                "/WEB-INF/layout/tiles.xml"
//        });
//        //启用刷新功能
//        tiles.setCheckRefresh(true);
//        return tiles;
//    }
//    //   配置Tile解析器
//    @Bean
//    public ViewResolver viewResolver2(){
//        return new TilesViewResolver();
//    }
    //我们为了使用html和thymeleaf作为视图，使用Thymeleaf的模板引擎：
    //Thymeleaf 视图解析器
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        //设置模板引擎：
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    //Thymeleaf模板引擎
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);//设置模板解析器
//        设置thymeleaf的Spring Security方言：
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
    //模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        //创建模板引擎：
        SpringResourceTemplateResolver templateResolver
                = new SpringResourceTemplateResolver();
        //设置前后缀
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        //设置模板类型：
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    //    配置multipart解析器：
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
         return new StandardServletMultipartResolver();
    }
////    添加login表单的视图映射：
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//    }

}
