/**
 * @author 60417
 * @date 2022/2/14
 * @time 11:41
 * @todo
 */
package com.yuyefanhua.spittr.data.impl;

import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.domain.Spitter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;
@Repository
public class JPASpitterRepository implements SpitterRepository {
    @PersistenceUnit
    private EntityManagerFactory emf;
    //为了避免代码的冗余，是否可以直接注入实体管理器？
    //EntityManager 并不是线程安全的，一般来讲并不适合注入到像 Repository 这样共享的单例 bean 中
    //但是借助PersistentContext注解我们可以设置EntityManager
    @PersistenceContext
    private EntityManager emger;
    @Override
    public long count() {
        return findAll().size();
    }
    @Override
    public Spitter save(Spitter spitter) {
//        emf.createEntityManager().persist(spitter);
        emger.persist(spitter);
        return spitter;
    }
    @Override
    public Spitter findOne(long id) {
//        return emf.createEntityManager().find(Spitter.class,id);
        return emger.find(Spitter.class,id);
    }
    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) emger.createQuery("select s from Spitter s where s.username= :username")
                //.createQuery("select s from Spitter s where s.username= ?1") 也行
                .setParameter("username", username).getSingleResult();
    }
    @Override
    public List<Spitter> findAll() {
        return (List<Spitter>) emger.createQuery("select s from Spitter s").getResultList();
    }
}
