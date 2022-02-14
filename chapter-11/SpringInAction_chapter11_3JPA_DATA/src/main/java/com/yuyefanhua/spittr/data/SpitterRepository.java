/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package com.yuyefanhua.spittr.data;

import com.yuyefanhua.spittr.domain.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpitterRepository extends JpaRepository<Spitter,Long>,SpitterStatus {
    /**
     * 根据用户名查找spitter
     * @param username 用户名
     * @return Spitter
     */
    Spitter findByUsername(String username);

    /**
     * 根据用户名或者姓来查找spitter
     * @param username 用户名
     * @param firstName 姓
     * @return List<Spitter>
     */
    List<Spitter> findByUsernameOrFirstNameLike(String username, String firstName);

    /**@Query注解 作用1
     * 定义自定义方法
     *查询所有邮箱为qq邮箱的用户spitter对象
     *
     * 用法2就是当DSL得到的方法名较长时，直接自定义好了。
     *
     * 注意table需要匹配对应的domain类
     */
    @Query("select s from Spitter s where s.email like '%qq.com'")
    List<Spitter> findAllQQSpitters();//

}
