import com.yuyefanhua.spittr.config.SpringJPADataConfiguration;
import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.domain.Spitter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author 60417
 * @date 2022/2/14
 * @time 12:21
 * @todo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@ContextConfiguration(classes = {SpringJPADataConfiguration.class})
public class JpaTest {
    //    注意一个repository
    @Autowired
    private SpitterRepository spitterRepository;
    @Test
    @Transactional
    public void updateSpitterStatus(){
        int i = spitterRepository.updateSpitterStatus();//1L的个数为
        assertEquals(1,i);
    }
    @Test
    @Transactional
    public void test_queryFindAllQQspitter(){
        List<Spitter> allQQSpitters = spitterRepository.findAllQQSpitters();
        System.out.println(allQQSpitters);
        assertEquals(6,allQQSpitters.size());
        assertSpitter(1,allQQSpitters.get(1));//H2数据库里面的全是qq用户
    }
    @Test
    @Transactional
    public void count() {
        assertEquals(6, spitterRepository.count());
    }
    @Test
    @Transactional
    public void findAll() {
        //查看数据库中的spitter的个数
        List<Spitter> spitters = spitterRepository.findAll();
        System.out.println(spitters.toString());
        assertEquals(6, spitters.size());
        assertSpitter(0, spitters.get(0));
        assertSpitter(1, spitters.get(1));
        assertSpitter(2, spitters.get(2));
        assertSpitter(3, spitters.get(3));
        assertSpitter(4, spitters.get(4));
        assertSpitter(5, spitters.get(5));
    }

    @Test
    @Transactional
    public void findByUsername() {
        //使用我们自设 DSL推导的实现
        assertSpitter(0, spitterRepository.findByUsername("wangerxiao"));
        assertSpitter(1, spitterRepository.findByUsername("lisi"));
        assertSpitter(2, spitterRepository.findByUsername("zhengkai"));
        assertSpitter(3, spitterRepository.findByUsername("niuer"));
    }

    @Test
    @Transactional
    public void findOne() {
        //使用默认的实现  getById
        assertSpitter(0, spitterRepository.getById(1L));
        assertSpitter(1, spitterRepository.getById(2L));
        assertSpitter(2, spitterRepository.getById(3L));
        assertSpitter(3, spitterRepository.getById(4L));
    }

    @Test
    @Transactional
    public void save_newSpitter() {
        assertEquals(6, spitterRepository.count());
        Spitter spitter = new Spitter(null, "niuer", "niuer", "niuer",
                "newbee@habuma.com", "yuyefanhua12345@qq.com");
        //使用默认的JPA实现 save
        Spitter saved = spitterRepository.save(spitter);
        //自定义实现count
        assertEquals(7, spitterRepository.count());
        assertSpitter(6, saved);//新插入的数据的id是7
        //使用默认的JPA实现 getById
        assertSpitter(4, spitterRepository.getById(5L));
    }
    //-------------以下是工具方法------------------
    /**
     * 比较 spitter是否一样
     * @param expectedSpitterIndex
     * @param actual
     */
    private static void assertSpitter(int expectedSpitterIndex, Spitter actual) {
        Spitter expected = SPITTERS[expectedSpitterIndex];
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    private static Spitter[] SPITTERS = new Spitter[7];
    /**
     * 执行前构造数据
     * 初始化方法   对于每一个测试方法都要执行一次
     * 与BeforeClass区别，后者是对于所有方法执行一次）
     */
    @BeforeClass
    public static void before() {
        SPITTERS[0] = new Spitter(1L,"王","二小","wangerxiao","123456","123456@qq.com");
        SPITTERS[1] = new Spitter(2L,"李","斯","lisi","123456","lisi@qq.com");
        SPITTERS[2] = new Spitter(3L,"郑","凯","zhengkai","123456","zhengkai@qq.com");
        SPITTERS[3] = new Spitter(4L,"牛","二","niuer","123456","niuer@qq.com");
        SPITTERS[4] = new Spitter(5L,"熊","大","xiongda","123456","xiongda@qq.com");
        SPITTERS[5] = new Spitter(6L,"熊","二","xionger","123456","xionger@qq.com");
        SPITTERS[6] = new Spitter(7L, "niuer", "niuer", "niuer",
                "newbee@habuma.com", "yuyefanhua12345@qq.com");
    }

}
