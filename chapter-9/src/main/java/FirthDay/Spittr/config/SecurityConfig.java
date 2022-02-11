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
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration  //java配置类
@EnableWebSecurity  //使用注解配置安全性
//@EnableWebMvcSecurity 过时，上面的注解已经具备它的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //方式1基于内存的方式 进行认证：配置内存的用户存储
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //inMemoryAuthentication方法 启用 内存用户存储
//        //使用withUser方法来创建一些用户位于内存中：
//        // user password USER
//        // admin password ADMIN
//        //用户 密码  角色权限
//        String password = new BCryptPasswordEncoder().encode("password");
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(password).roles("USER").and()
//                .withUser("admin").password(password).roles("USER","ADMIN");
//    }

    //方式2：基于数据库表进行认证：使用authenticationManagerBuilder的 jdbcAuthentication方法
//需要一个dataSource
//    @Autowired
//    DataSource dataSource;//使用注解进行自动注入
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //auth.jdbcAuthentication().dataSource(dataSource);
//        //使用自己的方式配置用户认证（不采用Spring Security内部默认的sql语句实现用户认证）
//        //重写认证和基本权限查询的语句，
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,true "+
//                        "from Spitter where username=?")
//                .authoritiesByUsernameQuery("select username,'ROLE_USER'" +
//                        "from Spitter where username=?")
//                .passwordEncoder(new StandardPasswordEncoder("123456"));
//                //groupAuthoritiesByUsername()通过重写这个方法更改第三条sql：将群组权限sql重写
//    }

//方式3、使用LDAP认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //--------1、设置密码和密码比对ldap认证
////        auth.ldapAuthentication()
////                .userSearchFilter("{uid={0}}")//设置ldap查询的用户过滤条件  两个大括号表示用户？
////                .groupSearchFilter("member={0}")//设置ldap查询的用户组过滤添加  1个大括号表示组？
////        //默认搜索是从LDAP层级结构的根结构开始，利用userSearchBasse可以修改起始搜索目录
////                .userSearchBase("ou=pepole")//在名为people的组织单元下搜索
////                .passwordCompare()
////                .passwordEncoder(new BCryptPasswordEncoder())//设置加密方式 密码转换器
////                .passwordAttribute("passcode");
//        //登录表单的密码默认会和LDAP条目里面的userPassword属性比对，如果不是这个属性名，须显式修改
//
//        //--------2、引用远程LDAP服务器的方式：contextSource()
////        auth
////                .ldapAuthentication()
////                .userSearchBase("ou=people")
////                .userSearchFilter("{uid={0}}")
////                .groupSearchBase("ou=groups")
////                .groupSearchFilter("member={0}")
////                //contextSource() 方法会返回一个 ContextSourceBuilder 对象
////                //这个对象除了其他功能以外，还提供了 url() 方法用来指定 LDAP 服务器的地址。
////                .contextSource()
////                .url("ldap://habuma.com:389/dc=habuma,dc=com");
//
//
//
//        //----------------3、引入嵌入式LDAP服务器：
////        auth.ldapAuthentication()
////                .userSearchFilter("{uid={0}}")
////                .groupSearchFilter("member={0}")
////                .userSearchBase("ou=pepole")
////                .contextSource().url("ldap://localhost:8399/dc=example,dc=com")
////        .root("dc=habuma,dc=com")//配置服务器，root为类目录下进行搜索：默认为根目录
////                .ldif("classpath:/users.ldif");//明确要求 LDAP 服务器从类路径根目录下的 users.ldif 文件中加载内容
//
//        /*
//        当 LDAP 服务器启动时，它会尝试在类路径下寻找 LDIF 文件来加载数据。
//        LDIF（LDAP Data Interchange Format，LDAP数据交换格式）是以文本文件展现 LDAP 数据的标准方式。
//        每条记录可以有一行或多行，每项包含一个名值对。记录之间通过空行进行分割。
//        如果你不想让 Spring 从整个根路径下搜索 LDIF 文件的话，那么可以通过调用 ldif() 方法
//        明确指定加载哪个 LDIF 文件：
//        .ldif("classpath:users.ldif");
//        //明确要求 LDAP 服务器从类路径根目录下的 users.ldif 文件中加载内容
//         */
//        //----------------4、配置自定义服务 UserDetailsService接口
        auth.userDetailsService(new SpitterUserService(spitterRepository))
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Autowired
    SpitterRepository spitterRepository;//自动注入


    //拦截请求：使用HttpSecurity对象：
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭CSRF防护功能，基于java的配置类默认开启csfr防护功能
        http
                //使用一个登录表单
                .formLogin()
                    .loginPage("/login")
                        .loginProcessingUrl("/")// 该方法如果使用post提交，需要控制器添加对应的方法
                .and()
                    .logout()
                        //.logoutUrl("/signout")
                                //设置logout的页面和需要的方法  因为默认开启CSRF时logout只能使用post方法
                        .logoutRequestMatcher(new AntPathRequestMatcher("/signout","GET"))
                            .logoutSuccessUrl("/")
                .and()
                    //这里设置退出，将会清除所有的remember me 的token 然后重定向到设定的目录
                    //开启token 记住我功能，设置有效时间和token名字
                    .rememberMe()
                        .tokenValiditySeconds(24190).
                            key("spittrKey")
                .and()//and()方法用来链接不同的配置指令，否则链式调用无法继续。
                .authorizeRequests()//请求需要鉴权
                    .antMatchers("spittr","/login", "/test","/user/login")// 不需要登录验证的请求
                .permitAll()
                    //个人主页需要有Spitter角色权限  这里添加/需要认证
                    .antMatchers("/spitter/me").hasRole("USER")
                    //提交 spittles需要Spitter角色权限
                    .antMatchers(HttpMethod.POST,"/spittles").hasRole("USER")
                //其余请求放过
                .anyRequest().permitAll()
                .and()
                    //需要配置HTTPS的通道的有对 /spitter/register请求
                    .requiresChannel()
                        .antMatchers("/spitter/register")
                            .requiresSecure();

    }
}
