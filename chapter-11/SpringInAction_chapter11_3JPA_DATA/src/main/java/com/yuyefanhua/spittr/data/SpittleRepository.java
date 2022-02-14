/**
 * @author 60417
 * @date 2022/1/17
 * @time 15:48
 * @todo
 */
package com.yuyefanhua.spittr.data;


import com.yuyefanhua.spittr.domain.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpittleRepository extends JpaRepository<Spittle,Long> {
    /**
     * 根据spittle 查找spittle
     * @param id
     * @return
     */
    Spittle findSpittleById(Long id);

    /**
     * 根据用户id查找其发布的spittle
     * @param spitterId
     * @return
     */
    List<Spittle> findSpittlesBySpitterId(long spitterId);
}
