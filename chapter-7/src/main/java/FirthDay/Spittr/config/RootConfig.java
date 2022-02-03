/**
 * @author 60417
 * @date 2022/1/16
 * @time 17:19
 * @todo
 */
package FirthDay.Spittr.config;

import FirthDay.Spittr.data.SpitterRepository;
import FirthDay.Spittr.data.SpittleRepository;
import FirthDay.Spittr.data.imp.SpitterRepositoryImp;
import FirthDay.Spittr.data.imp.SpittleRepositoryImp;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"FirthDay.Spittr"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)
        })
@EnableWebMvc
public class RootConfig {
        @Bean
        public SpittleRepository spittleRepository(){
                return new SpittleRepositoryImp();
        }
        @Bean
        public SpitterRepository spitterRepository(){
                return new SpitterRepositoryImp();
        }
//        配置一个MessageSource用来加载ResourceBundle的资源文件，用于国际化
        @Bean
        public MessageSource messageSource(){
                ResourceBundleMessageSource messageSource
                        = new ResourceBundleMessageSource();
                messageSource.setBasename("message");//这里则会查找文件前缀为messages的配置文件
                //这里：由于MessagConverter的默认编码是ISO-8859-1,不支持中文
                //我们修改为UTF-8
                messageSource.setDefaultEncoding("UTF-8");
                return  messageSource;
        }
}
