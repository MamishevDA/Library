package main.java.ru.mamishev.model;

/**
 * Created by mamishevda on 25.07.2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Controller
@Component*/
@Repository
public class UserImpl {


    private JdbcTemplate jdbcTemplate;
    private List<User> users;

    @Autowired
    public UserImpl(JdbcTemplate jdbcOperations) {
        this.jdbcTemplate = jdbcOperations;
    }

    public void create(String name, String pwd) {
        String SQL = "insert into users(name,PWD) values (?,?)";
        this.jdbcTemplate.update(SQL, name, pwd);
        System.out.println("Created Record Name = " + name);
    }

    public User getUser(Integer id) {
        String SQL = "select * from users where id = ?";
        User users = this.jdbcTemplate.queryForObject(SQL,
                new Object[]{id}, new UserRowMapper());
        return users;
    }

    public User getUserByName(String name) {
        String SQL = "select * from users where name = ?";
        User users = this.jdbcTemplate.queryForObject(SQL,
                new Object[]{name}, new UserRowMapper());
        return users;
    }

    public int isUserExist(String name) {
        String SQL = "select count(1) from users where name = ?";
        int count = this.jdbcTemplate.queryForObject(SQL,
                new Object[]{name}, Integer.class);
        return count;
    }

    public List<User> getUserLists() {
        String SQL = "select * from users order by name";
        users = this.jdbcTemplate.query(SQL,
                new UserRowMapper());
        return users;
    }

    public void updateUser(String user, String pwd) {
        String SQL = "update users set pwd = ? where name = ?";
        jdbcTemplate.update(SQL, pwd, user);
    }

    public User getUser(String name, String pwd) {
        String SQL = "select * from users where name = ? and pwd = ?";
        User user = this.jdbcTemplate.queryForObject(SQL,
                new Object[]{name, pwd}, new UserRowMapper());
        return user;
    }

    public int isUserInUse(String name) {
        String SQL = "select count(1) from BOOKS b inner JOIN users u on b.TAKEDBYUSER = u.ID where u.NAME = ?";
        int count = jdbcTemplate.queryForObject(SQL,
                new Object[]{name}, Integer.class);
        return count;
    }

    public String getUserRole(User s) {
        String SQL = "select role_name from user_roles where id = ?";
        String res = this.jdbcTemplate.queryForObject(SQL, new Object[]{s.getId_role()}, String.class);
        return res;
    }

    private void refreshList() {
        this.users = getUserLists();
    }

    public void delete(Integer id) {
        String SQL = "delete from users where id = ?";
        this.jdbcTemplate.update(SQL, id);
        refreshList();
    }

    public void delete(String name) {
        String SQL = "delete from users where name = ?";
        this.jdbcTemplate.update(SQL, name);
        refreshList();
    }


}