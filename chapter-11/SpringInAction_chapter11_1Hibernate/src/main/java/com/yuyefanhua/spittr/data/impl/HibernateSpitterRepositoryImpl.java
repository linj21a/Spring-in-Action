/**
 * @author 60417
 * @date 2022/2/13
 * @time 16:15
 * @todo
 */
package com.yuyefanhua.spittr.data.impl;

import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.domain.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateSpitterRepositoryImpl implements SpitterRepository {
    @Autowired
    private SessionFactory sessionFactory;
//    @Inject   //也可以在SessionFactory上面使用Autowired自动注入
    public HibernateSpitterRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HibernateSpitterRepositoryImpl() {
    }

    private Session currentSession(){
        //获取当前事务的session
        return sessionFactory.getCurrentSession();
    }
    @Override
    public Spitter findByUsername(String username) {
        //createCriteria这个方法在5.2以后过时了，相关的特性移植到了javax JPA里面
        return (Spitter) currentSession().createCriteria(Spitter.class)
                .add(Restrictions.eq("username",username))
                .list().get(0);
    }
    @Override
    public long count() {
        return findAll().size();
    }
    @Override
    public List<Spitter> findAll() {
        return currentSession().createCriteria(Spitter.class).list();
    }
    /**
     * 更新一个spittr
     * @param spitter
     * @return
     */
    @Override
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((Long)id,
                spitter.getFirstName(), spitter.getLastName(),
                spitter.getUsername(), spitter.getPassword(), spitter.getEmail());
    }

    @Override
    public Spitter findOne(long id) {
        return currentSession().get(Spitter.class, id);
    }






}
