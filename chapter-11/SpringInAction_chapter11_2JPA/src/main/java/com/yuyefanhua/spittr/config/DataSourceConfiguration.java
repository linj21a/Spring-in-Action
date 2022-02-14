/**
 * @author 60417
 * @date 2022/2/13
 * @time 19:35
 * @todo
 */
package com.yuyefanhua.spittr.config;

import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.data.SpittleRepository;
import com.yuyefanhua.spittr.data.impl.JPASpitterRepository;
import com.yuyefanhua.spittr.data.impl.JpaSpittleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    public SpitterRepository spitterRepository(){
        return new JPASpitterRepository();
    }
    @Bean
    public SpittleRepository spittleRepository(){
        return new JpaSpittleRepository();
    }

    /**
     * 嵌入式数据库  H2
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        edb.addScript("spittr/db/jpa/schema.sql");
        edb.addScript("spittr/db/jpa/test-data.sql");
        return edb.build();
    }
}
