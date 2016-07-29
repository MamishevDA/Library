package main.java.ru.mamishev.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mamishevda on 25.07.2016.
 */
public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("NAME"));
            user.setId_role(rs.getLong("id_role"));
            user.setPwd(rs.getString("pwd"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
