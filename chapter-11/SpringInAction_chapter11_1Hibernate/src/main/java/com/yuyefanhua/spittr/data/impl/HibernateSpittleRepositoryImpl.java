/**
 * @author 60417
 * @date 2022/2/13
 * @time 19:04
 * @todo
 */
package com.yuyefanhua.spittr.data.impl;

import com.yuyefanhua.spittr.data.SpittleRepository;
import com.yuyefanhua.spittr.domain.Spittle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateSpittleRepositoryImpl implements SpittleRepository {
    @Autowired
    private SessionFactory sessionFactory;
    //    @Inject   //也可以在SessionFactory上面使用Autowired自动注入
    public HibernateSpittleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HibernateSpittleRepositoryImpl() {
    }

    private Session currentSession(){
        //获取当前事务的session
        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long spittleId) {
        return currentSession().get(Spittle.class, spittleId);
    }

    @Override
    public void save(Spittle spittle) {
        return;
    }
    @Override
    public long count(){
        return findAll().size();
    }
    @Override
    public List<Spittle> findAll() {
        return (List<Spittle>) currentSession().createCriteria(Spittle.class).list();
    }
}
