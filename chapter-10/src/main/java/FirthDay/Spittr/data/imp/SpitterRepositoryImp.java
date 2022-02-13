/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:27
 * @todo
 */
package FirthDay.Spittr.data.imp;

import FirthDay.Spittr.Bean.Spitter;
import FirthDay.Spittr.data.SpitterRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SpitterRepositoryImp implements SpitterRepository {
    private static final String INSERT_SPITTER =
            "insert into spitter(firstName,lastName,username,password,email) value(?,?,?,?,?)";
    private static final String SELECT_SPITTER =
            "select id, username,password,firstname,lastname, email from spitter";

    private JdbcTemplate jdbcTemplate;
    public SpitterRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    private JdbcOperations jdbcOperations;
//    @Inject //该注解是javax的注解
//    public SpitterRepositoryImp(JdbcOperations jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    /**
     * 添加一个spitter到表中
     * @param spitter
     */
    public void addSpitter(Spitter spitter) {
        jdbcTemplate.update(INSERT_SPITTER,
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getEmail()
        );
    }

    /**
     * 更新spitter的信息
     * @param spitter 要更新的spitter内容
     * @return 返回更新后的spitter
     */
    @Override
    public Spitter save(Spitter spitter) {
        spitter.setId(24L);
        return spitter;
    }

    /**
     * 按照用户名查询spitter返回
     * @param username 用户名
     * @return spitter
     */
    @Override
    public Spitter findSpitterByName(String username) {
        String query = SELECT_SPITTER+" where username=?";
        return jdbcTemplate.queryForObject(query,new spitterRowMapper(),username);
    }

    /**
     *按照用户id查询对应的spitter信息
     * @param id 要查询的用户id
     * @return spitter
     */
    public Spitter findSpitterById(int id){
        String query = SELECT_SPITTER+"  where id=?";
        return jdbcTemplate.queryForObject(query, Spitter.class,id);
    }
    public List<Spitter> findAll(){
        String query = SELECT_SPITTER+" order by id";
        return jdbcTemplate.query(query,new spitterRowMapper());
    }

    /**
     * 查询全部用户时需要用到行映射 spitterRowMapper
     */
    private static class spitterRowMapper implements RowMapper<Spitter>{

        @Override
        public Spitter mapRow(ResultSet rs, int i) throws SQLException {
            long id = rs.getLong("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            return new Spitter(id,firstname,lastname,username,password,email);
        }
    }
}
