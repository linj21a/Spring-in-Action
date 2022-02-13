/**
 * @author 60417
 * @date 2022/1/17
 * @time 19:37
 * @todo
 */
package FirthDay.Spittr.data.imp;

import FirthDay.Spittr.Bean.Spittle;
import FirthDay.Spittr.data.SpittleRepository;
import FirthDay.Spittr.web.DuplicateSpittleException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpittleRepositoryImp implements SpittleRepository {
    private JdbcTemplate jdbcTemplate;
    public SpittleRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Spittle> findSpittles(long max, int count) {
       return createSpittles(count);
    }

    @Override
    public Spittle findOne(long spittleId) {
        return null;
    }

    @Override
    public void save(Spittle spittle) throws DuplicateSpittleException {
        throw new DuplicateSpittleException();
    }

    private List<Spittle> createSpittles(int count){
        //这里的就是一个DAO 数据接口实现：
        //我们这里先写死，因为作者书本里面没有过多的涉及这里的细节（第10章才涉及）
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle" + i, new Date()));
        }
        return spittles;
    }
}
