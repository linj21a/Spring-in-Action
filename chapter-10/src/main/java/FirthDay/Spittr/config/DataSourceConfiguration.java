package FirthDay.Spittr.config;

import FirthDay.Spittr.data.SpitterRepository;
import FirthDay.Spittr.data.SpittleRepository;
import FirthDay.Spittr.data.imp.SpitterRepositoryImp;
import FirthDay.Spittr.data.imp.SpittleRepositoryImp;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * 通过使用 profile 功能，会在运行时选择数据源，这取决于哪一个 profile 处于激活状态。
 */
@Configuration
public class DataSourceConfiguration {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate){
        return new SpitterRepositoryImp(jdbcTemplate);
    }
    @Bean
    public SpittleRepository spittleRepository(JdbcTemplate jdbcTemplate){
        return new SpittleRepositoryImp(jdbcTemplate);
    }
    /**
     * 嵌入式数据库的配置——在开发环境使用
     * 当且仅当 development profile 处于激活状态时，会创建嵌入式数据库
     * @return dataSource
     */
    @Profile("development")
    @Bean
    public DataSource embeddedDataSource(){
//        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
//        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);//设置类型
//        embeddedDatabaseBuilder.addScript("classpath:schema.sql");
//        embeddedDatabaseBuilder.addScript("classpath:test-data.sql");
//        return embeddedDatabaseBuilder.build();
        //可以使用建造者模式：
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }
    /**
     * 测试环境的数据源 dbcp
     * 当且仅当 qa profile 处于激活状态时，会创建 DBCP BasicDataSource
     * @return BasicDataSource
     */
    @Profile("qa")
    @Bean
    public DataSource data(){
        BasicDataSource dbcp = new BasicDataSource();
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");//设置驱动的类路径
        dbcp.setUrl("jdbc:mysql://localhost:3306/spittr?" +
                "autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
        dbcp.setUsername("root");
        dbcp.setPassword("lh123321root");
        //其他参数
        dbcp.setInitialSize(5);
        dbcp.setMaxIdle(10);//最大同时处理的连接数
        return dbcp;
    }
    /**
     * 生产环境的，则从服务器的JNDI上获取
     * 当且仅当 production profile 处于激活状态 时，会从 JNDI 获取数据源。
     * @return JndiObjectFactoryBean
     */
    @Profile("production")
    @Bean
    public DataSource dataSource(){
        JndiObjectFactoryBean objectFactoryBean = new JndiObjectFactoryBean();
        objectFactoryBean.setJndiName("jdbc/SpittrDS");//在jndi中的资源位置
        objectFactoryBean.setResourceRef(true);
        objectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return ((DataSource) objectFactoryBean.getObject());
    }
}
