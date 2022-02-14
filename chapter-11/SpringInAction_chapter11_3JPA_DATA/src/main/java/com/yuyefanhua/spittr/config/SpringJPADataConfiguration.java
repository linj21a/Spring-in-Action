/**
 * @author 60417
 * @date 2022/2/14
 * @time 13:34
 * @todo
 */
package com.yuyefanhua.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
//该注解会生成一个 （继承了JpaRepository接口的接口类）的一个类。
@EnableJpaRepositories(basePackages = "com.yuyefanhua.spittr.data")
public class SpringJPADataConfiguration {
    //配置一个实体管理器：
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setPersistenceUnitName("Spitter");
        lemfb.setPackagesToScan("com.yuyefanhua.spittr.domain");//必须一起设置才能够找到
        lemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lemfb.setDataSource(dataSource);
        return lemfb;
    }
    @Bean
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean lemfb){
        return lemfb.getObject();
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        //设置构建的sql方言
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return hibernateJpaVendorAdapter;
    }
    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.addScript("db/jpa/schema.sql");
        databaseBuilder.addScript("db/jpa/test-data.sql");
        databaseBuilder.setType(EmbeddedDatabaseType.H2);
        return databaseBuilder.build();
    }
//    事务管理器：
    //这里需要使用同一个实体管理工厂，否则如果是下面的则导致SpitterRepositoryImpl里面的EntityManager无法注入
    @Bean
    public JpaTransactionManager transactionManager(){
        return new JpaTransactionManager();
    }
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                entityManagerFactory(dataSource(),jpaVendorAdapter()));
//        return transactionManager;
//    }
}
