package main.java.ru.mamishev.api;

import main.java.ru.mamishev.model.User;
import main.java.ru.mamishev.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Created by MamishevDA on 29.07.2016.
 */
@Repository
public class AuthUser {
    @Autowired
    UserImpl user;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = "";
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }
        User u = user.getUserByName(username);
        return u;
    }
}
