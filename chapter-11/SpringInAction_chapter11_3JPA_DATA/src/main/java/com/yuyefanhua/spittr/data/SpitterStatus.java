/**
 * @author 60417
 * @date 2022/2/14
 * @time 22:09
 * @todo
 */
package com.yuyefanhua.spittr.data;

public interface SpitterStatus {
    /**
     * //我们来写一个方法，使得无法用JPARepository来生成
     *     假设spitter有一个status的成员  我们这里没有，直接使用lastName代替
     *    当spitter 发表了超过1000条sppittle的时候，将其status设置为牛逼（niubi）
     *     很明显我们这个时候需要使用表连接，这是无法使用@Query实现的
     *
     * @return int
     */
    int updateSpitterStatus();
}
