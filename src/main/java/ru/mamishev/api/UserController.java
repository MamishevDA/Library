package main.java.ru.mamishev.api;

import main.java.ru.mamishev.model.User;
import main.java.ru.mamishev.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MamishevDA on 29.07.2016.
 */
@RestController
public class UserController {

    @Autowired
    UserImpl user;

    @RequestMapping("/getUser")
    public User getUser(@RequestParam String name) {
        return user.getUserByName(name);
    }

}
