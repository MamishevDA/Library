package test.java;

/**
 * Created by MamishevDA on 24.07.2016.
 */

import liquibase.exception.LiquibaseException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConfig {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, LiquibaseException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:mem:default");
       /* Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
        Liquibase liquibase = new liquibase.Liquibase("main/resources/DBLogFile.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());
*/
        Statement s = conn.createStatement();
        s.executeUpdate("CREATE TABLE users (\n" +
                "  id         INTEGER PRIMARY KEY,\n" +
                "  name VARCHAR(30)\n" +
                ")");
        ResultSet rs = s.executeQuery("select count(1) s from USERS");
        while (rs.next()) {
            System.out.println(rs.getInt("s"));
        }


        conn.close();
    }


    public JdbcTemplate getJdbcTemplate() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setDriverClassName((Class.forName("org.h2.Driver").getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl("jdbc:h2:mem:default");
        dataSource.setUsername("");
        dataSource.setPassword("");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("CREATE TABLE users (\n" +
                "  id bigint auto_increment PRIMARY KEY,\n" +
                "  name VARCHAR(30)\n" +
                ");\n");
        return jdbcTemplate;
    }


    public int getDataSource() {
        //   Connection conn;
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:mem:default");

            {
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE users (\n" +
                        "  id         INTEGER PRIMARY KEY,\n" +
                        "  name VARCHAR(30)\n" +
                        ")");
                ResultSet rs = s.executeQuery("select count(1) s from USERS");
                rs.next();
                return rs.getInt("s");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
