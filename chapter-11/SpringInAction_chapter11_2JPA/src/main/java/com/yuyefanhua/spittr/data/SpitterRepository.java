/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package com.yuyefanhua.spittr.data;

import com.yuyefanhua.spittr.domain.Spitter;

import java.util.List;

public interface SpitterRepository {
    long count();

    Spitter save(Spitter spitter);

    Spitter findOne(long id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();
}
