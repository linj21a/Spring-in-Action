/**
 * @author 60417
 * @date 2022/1/17
 * @time 15:48
 * @todo
 */
package FirthDay.Spittr.data;

import FirthDay.Spittr.Bean.Spittle;
import FirthDay.Spittr.web.DuplicateSpittleException;

import java.util.List;

public interface SpittleRepository {
    /**
     * 查找Spittles列表
     * @param max 所查找Spittles的Spittles ID最大值
     * @param count 要查找的list大小，返回Spittle的个数
     * @return
     */
    List<Spittle> findSpittles(long max, int count);

    /**
     * 根据spittle的ID进行查找
     * @param spittleId
     * @return spittle
     */
    Spittle findOne(long spittleId);
    public void save(Spittle spittle) throws DuplicateSpittleException;
}
