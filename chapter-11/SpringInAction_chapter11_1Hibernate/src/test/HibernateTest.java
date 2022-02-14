/**
 * @author 60417
 * @date 2022/2/13
 * @time 19:00
 * @todo
 */

import com.yuyefanhua.spittr.config.HibernateConfiguration;
import com.yuyefanhua.spittr.data.SpitterRepository;
import com.yuyefanhua.spittr.domain.Spitter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//导入配置类
@ContextConfiguration(classes = HibernateConfiguration.class)
public class HibernateTest {
//    注意一个repository
    @Autowired
    private SpitterRepository spitterRepository;
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
        assertSpitter(0, spitterRepository.findByUsername("wangerxiao"));
        assertSpitter(1, spitterRepository.findByUsername("lisi"));
        assertSpitter(2, spitterRepository.findByUsername("zhengkai"));
        assertSpitter(3, spitterRepository.findByUsername("niuer"));
    }

    @Test
    @Transactional
    public void findOne() {
        assertSpitter(0, spitterRepository.findOne(1L));
        assertSpitter(1, spitterRepository.findOne(2L));
        assertSpitter(2, spitterRepository.findOne(3L));
        assertSpitter(3, spitterRepository.findOne(4L));
    }

    @Test
    @Transactional
    public void save_newSpitter() {
        assertEquals(6, spitterRepository.count());
        Spitter spitter = new Spitter(null, "niuer", "niuer", "niuer",
                "newbee@habuma.com", "yuyefanhua12345@qq.com");
        Spitter saved = spitterRepository.save(spitter);
        assertEquals(7, spitterRepository.count());
        assertSpitter(6, saved);//新插入的数据的id是7
        assertSpitter(4, spitterRepository.findOne(5L));
    }

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
