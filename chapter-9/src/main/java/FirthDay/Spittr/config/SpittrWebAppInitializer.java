/**
 * @author 60417
 * @date 2022/1/16
 * @time 16:13
 * @todo
 */
package FirthDay.Spittr.config;
//一个类

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Spring会查找实现了WebApplicationInitializer的类，进行加载配置、转发器配置、filter配置等
 */
public class SpittrWebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    //将DispatcherServlet映射到“/”,表示所有的请求都经过该转发器
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {//指定配置类
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 配置multipart请求 用于上传文件需求
     * @param registration 所注册的转发器对象
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        //如果这个临时目录不存在，它不会被创建
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(null,
                2097152, 4194304, 0);
        registration.setMultipartConfig(multipartConfigElement);
        //2MB,4MB,文件都写入到磁盘
    }
//    增加一个过滤器，设置文件的编码：

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
}
