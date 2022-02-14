/**
 * @author 60417
 * @date 2022/2/13
 * @time 19:35
 * @todo
 */
package com.yuyefanhua.spittr.config;

import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.data.SpittleRepository;
import com.yuyefanhua.spittr.data.impl.HibernateSpitterRepositoryImpl;
import com.yuyefanhua.spittr.data.impl.HibernateSpittleRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class dataSourceConfig {
    @Bean
    public SpitterRepository spitterRepository(){
        return new HibernateSpitterRepositoryImpl();
    }
    @Bean
    public SpittleRepository spittleRepository(){
        return new HibernateSpittleRepositoryImpl();
    }
}
