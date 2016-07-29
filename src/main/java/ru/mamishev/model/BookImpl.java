package main.java.ru.mamishev.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mamishevda on 25.07.2016.
 */
/*@Controller
@Component*/
@Repository
public class BookImpl {
    private List<Book> books;
    private JdbcTemplate jdbcTemplate;

    private Long id;
    private String isn;
    private String autor;
    private String title;
    private Long takedbyuser;

    @Autowired
    public BookImpl(JdbcTemplate jdbcOperations) {
        this.jdbcTemplate = jdbcOperations;
    }

    @Override
    public String toString() {
        return "BookImpl{" +
                ", id=" + id +
                ", isn='" + isn + '\'' +
                ", author='" + autor + '\'' +
                ", title='" + title + '\'' +
                ", takedbyuser=" + takedbyuser +
                '}';
    }

    public void create(String isn, String author, String title) {
        String SQL = "insert into Books(isn,author,title) values (?,?,?)";
        jdbcTemplate.update(SQL, isn, author, title);
    }

    public Book getBook() {
        String SQL = "select * from books where id = ?";
        Book book = jdbcTemplate.queryForObject(SQL,
                new Object[]{id}, new BookRowMapper());
        return book;
    }

    public Book getBook(String isn) {
        String SQL = "select * from books where isn = ?";
        Book book = jdbcTemplate.queryForObject(SQL,
                new Object[]{isn}, new BookRowMapper());
        return book;
    }

    public int isPresentBook(String isn) {
        String SQL = "select count(1) from books where isn = ?";
        int count = jdbcTemplate.queryForObject(SQL,
                new Object[]{isn}, Integer.class);
        return count;
    }

    public List<Book> getBookLists() {
        String SQL = "select * from books order by title";
        books = jdbcTemplate.query(SQL,
                new BookRowMapper());
        return books;
    }

    public List<Book> getPagedBookList(int startIndex, int count) {
        String SQL = "select * from books order by title LIMIT ? OFFSET ?";
        books = jdbcTemplate.query(SQL, new Object[]{count, startIndex},
                new BookRowMapper());
        return books;
    }

    public List<Book> getPagedBookListWithUsers(int startIndex, int count, String currentUser) {
        String SQL = "select b.*,decode(u.name,?,'вернуть',nvl(u.name,'взять')) name from BOOKS b LEFT JOIN users u on b.TAKEDBYUSER = u.ID order by b.TITLE LIMIT ? OFFSET ?";
        books = jdbcTemplate.query(SQL, new Object[]{currentUser, count, startIndex},
                new BookAndUserRowMapper());
        return books;
    }

    public void delete(Integer id) {
        String SQL = "delete from books where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    public void delete(String isn) {
        String SQL = "delete from books where isn = ?";
        jdbcTemplate.update(SQL, isn);
        System.out.println("Deleted Record with isn = " + isn);
    }

    public void updateTakedByUser(String bookISN, Integer userID) {
        String SQL = "update books set takedbyuser = ? where isn = ?";
        jdbcTemplate.update(SQL, userID, bookISN);
    }

    public void updateBook(Integer bookID, String isn, String author, String title) {
        String SQL = "update books set isn = ?, author = ?, title = ? where id = ?";
        jdbcTemplate.update(SQL, isn, author, title, bookID);
    }
}
