package main.java.ru.mamishev.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MamishevDA on 29.07.2016.
 */

public class BookAndUserRowMapper implements RowMapper {
    public Book mapRow(ResultSet rs, int rowNum) {
        Book book = new Book();
        try {
            book.setId(rs.getLong("id"));
            book.setIsn(rs.getString("ISN"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setTakedbyuser(rs.getLong("takedByUser"));
            book.setUser(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}