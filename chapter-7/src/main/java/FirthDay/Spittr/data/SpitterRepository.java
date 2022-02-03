/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package FirthDay.Spittr.data;

import FirthDay.Spittr.Bean.Spitter;

public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findSpitterByName(String username);
}
