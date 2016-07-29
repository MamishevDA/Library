package main.java.ru.mamishev.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mamishevda on 25.07.2016.
 */
public class BookRowMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet rs, int rowNum) {
        Book book = new Book();
        try {
            book.setId(rs.getLong("id"));
            book.setIsn(rs.getString("ISN"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setTakedbyuser(rs.getLong("takedByUser"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
