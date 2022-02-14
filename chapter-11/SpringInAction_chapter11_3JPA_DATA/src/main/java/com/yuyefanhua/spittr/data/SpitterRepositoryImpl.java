/**
 * @author 60417
 * @date 2022/2/14
 * @time 22:08
 * @todo
 */
package com.yuyefanhua.spittr.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class SpitterRepositoryImpl implements SpitterStatus{
//    注入一个实体管理器，必须使用PersistenceContext保证线程安全和持久化的实体管理器
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager emg;
    @Override
    public int updateSpitterStatus() {
//        String sql = "update Spitter spitter set spitter.lastName='niubi' " +
//                "  where spitter.id IN (" +
//                "SELECT s FROM Spitter s join Spittle on spitter.id=Spittle .spitterId and " +
//                "  (SELECT COUNT(spittles) FROM Spittle spittles group by spittles.spitterId) > 0)";
        String sql = "update Spitter spitter set spitter.lastName='niubi' where spitter.id=1";
        return emg.createQuery(sql).executeUpdate();
    }
}
