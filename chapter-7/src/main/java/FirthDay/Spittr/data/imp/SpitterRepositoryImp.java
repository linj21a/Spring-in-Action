/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:27
 * @todo
 */
package FirthDay.Spittr.data.imp;

import FirthDay.Spittr.Bean.Spitter;
import FirthDay.Spittr.data.SpitterRepository;

public class SpitterRepositoryImp implements SpitterRepository {
    @Override
    public Spitter save(Spitter spitter) {
        spitter.setId(24L);
        return spitter;
    }
    @Override
    public Spitter findSpitterByName(String username) {
        return new Spitter(24L,"hu","lulu","luluhu","1234","");
    }
}
