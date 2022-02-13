/**
 * @author 60417
 * @date 2022/2/6
 * @time 20:55
 * @todo
 */
package FirthDay.Spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer用于初始化DelegatingFilterProxy和默认的FilterChain，
 * 它实现自WebApplicationInitializer所以它的初始化方法能够在Servlet容器时被调用。
 *
 * Spring 会加载实现了WebApplicationInitializer的类，并用它在Web容器中注册DelegatingFilterProxy。
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
