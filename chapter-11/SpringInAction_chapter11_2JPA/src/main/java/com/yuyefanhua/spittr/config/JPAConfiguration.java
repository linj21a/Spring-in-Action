/**
 * @author 60417
 * @date 2022/2/14
 * @time 11:02
 * @todo
 */
package com.yuyefanhua.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * jpa的配置类
 */
@Configuration
@ComponentScan
@Transactional
public class JPAConfiguration {
    //持久化类需要一个实体管理器工厂，spring5推荐使用的是LocalEntityManagerFactoryBean

    /**
     * 方式1、基于XML配置文件的应用程序管理类下的JPA
     * @return
     */
//    @Bean
//    public LocalEntityManagerFactoryBean entityManagerFactoryBean(){
//        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
//        emfb.setPersistenceUnitName("spitterPU");//持久化文件里的持久化单元的name指向的配置信息
////        emfb.setJpaDialect();
//        return emfb;
//    }
    /**
     * 方式2、容器管理的JAP
     * @param dataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter
            ){
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setPackagesToScan("com.yuyefanhua.spittr.domain");
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        return lcemfb;
    }

    /**
     * 方式3、使用JNDI从服务器获取EntityManagerFactory
     * xml配置方式：<jee:jndi-lookup id="emf" jndi-name="persistence/spitterPU" />
     *  --jndi-name 表示jndi目录下的资源路径
     * @return
     */
//    @Bean
//    public JndiObjectFactoryBean jndiObjectFactoryBean(){
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("jdbc/spittrDS");//jdni目录资源路径
//        jndiObjectFactoryBean.setResourceRef(true);
//        return jndiObjectFactoryBean;
//    }
    /**
     * 设置一个dataSource实现
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
    /**
     * 使用的特定JPA实现厂商 配置细节
     * @return HibernateJpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        //我们这里常用的 JPA实现一般采用的是Hibernate
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        //最主要的属性是“database”，用来指定使用的数据库类型。
        // 从而根据数据库类型决定如何将数据库特定异常转换为Spring一致性异常。

        jpaVendorAdapter.setDatabase(Database.H2);//设置使用的数据库类型
        //generateDdl= false表示不自动生成DDL
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(true);
//        构建sql的方言
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return jpaVendorAdapter;
    }
    //需要配置一个事务管理器，否则实体管理器不知道返回哪一个事务的连接实体。
    // 而且回滚操作需要在一个事务上下文中执行。
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBean(dataSource(),jpaVendorAdapter()).getObject()
        );
        return transactionManager;
    }

}
