/**
 * @author 60417
 * @date 2022/2/14
 * @time 12:06
 * @todo
 */
package com.yuyefanhua.spittr.data.impl;

import com.yuyefanhua.spittr.data.SpittleRepository;
import com.yuyefanhua.spittr.domain.Spittle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaSpittleRepository implements SpittleRepository {
    @PersistenceContext
    private EntityManager emger;//不是真正注入，而是一个代理
    @Override
    public List<Spittle> findSpittles(long max, int count) {
//        必须大写表名首字母，和实体类映射，且不支持* 和limit
        return emger.createQuery("select s from Spittle s").setMaxResults(count).getResultList();
    }
    @Override
    public Spittle findOne(long spittleId) {
        return emger.find(Spittle.class, spittleId);
    }
    @Override
    public void save(Spittle spittle) {
        emger.persist(spittle);
    }
    @Override
    public long count() {
        return findAll().size();
    }
    @Override
    public List<Spittle> findAll() {
        return (List<Spittle>) emger.createQuery("select s from Spittle s").getResultList();
    }
}
