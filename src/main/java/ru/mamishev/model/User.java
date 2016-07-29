package main.java.ru.mamishev.model;

/**
 * Created by MamishevDA on 24.07.2016.
 */

public class User {
    private int id;
    private String name;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    private Long id_role;

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(final Long id_role) {
        this.id_role = id_role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}