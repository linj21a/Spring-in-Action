/**
 * @author 60417
 * @date 2022/2/5
 * @time 21:39
 * @todo
 */
package FirthDay.Spittr.config;

import FirthDay.Spittr.data.SpitterRepository;
import FirthDay.Spittr.service.SpitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  //java配置类
@EnableWebSecurity  //使用注解配置安全性
//@EnableWebMvcSecurity 过时，上面的注解已经具备它的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //----------------4、配置自定义服务 UserDetailsService接口
        auth.userDetailsService(new SpitterUserService(spitterRepository))
                .passwordEncoder(new BCryptPasswordEncoder());

    }
    @Autowired
    SpitterRepository spitterRepository;//自动注入


    //拦截请求：使用HttpSecurity对象：
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .formLogin()
                .loginPage("/login")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .logoutSuccessUrl("/")
            .and()
                .rememberMe()
//                内存token的仓库实现
                .tokenRepository(new InMemoryTokenRepositoryImpl())
                .tokenValiditySeconds(2419200)
                .key("spittrKey")
            .and()
                .httpBasic()
                .realmName("Spittr")
                .and()
            .authorizeRequests()
                .antMatchers("/spitter/register").permitAll()
//                .antMatchers("/").hasRole("SPITTER")
//                .antMatchers("/spitter/me").hasRole("SPITTER")
                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
            .anyRequest().permitAll();

    }
}
