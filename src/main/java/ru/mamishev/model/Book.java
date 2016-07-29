package main.java.ru.mamishev.model;

/**
 * Created by MamishevDA on 24.07.2016.
 */

public class Book {
    private Long id;
    private String isn;
    private String autor;
    private String title;
    private Long takedbyuser;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public String getAuthor() {
        return autor;
    }

    public void setAuthor(String autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTakedbyuser() {
        return takedbyuser;
    }

    public void setTakedbyuser(Long takedbyuser) {
        this.takedbyuser = takedbyuser;
    }
}