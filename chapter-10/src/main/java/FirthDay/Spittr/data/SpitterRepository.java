/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package FirthDay.Spittr.data;

import FirthDay.Spittr.Bean.Spitter;

import java.util.List;

public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findSpitterByName(String username);

    void addSpitter(Spitter spitter);
    /**
     *按照用户id查询对应的spitter信息
     * @param id 要查询的用户id
     * @return spitter
     */
    Spitter findSpitterById(int id);
    List<Spitter> findAll();
}
