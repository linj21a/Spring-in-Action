/**
 * @author 60417
 * @date 2022/2/6
 * @time 22:52
 * @todo
 */
package FirthDay.Spittr.service;

import FirthDay.Spittr.Bean.Spitter;
import FirthDay.Spittr.data.SpitterRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * SpitterUserService 需要实现UserDetailsService接口
 * 覆写：loadUserByUsername方法 返回一个UserDetails
 */
public class SpitterUserService implements UserDetailsService {
    //它负责底层接口api的提供
    private final SpitterRepository spitterRepository;
    //构造器注入的方式
    public SpitterUserService(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }
    //提供的服务：
    @Override
    public UserDetails loadUserByUsername(String username){
        Spitter spitter = spitterRepository.findSpitterByName(username);
        if(spitter!=null){
            System.out.println(spitter);
            //创建权限列表：
            List<GrantedAuthority> authorityList
                    = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_SPITTER"));//添加一个权限角色
            return new User(spitter.getUsername(),
                    new BCryptPasswordEncoder().encode(spitter.getPassword())
                    ,authorityList);
        }
        System.out.println("spitter null");
        throw new UsernameNotFoundException("用户: "+username+" 不存在！");
    }
}
