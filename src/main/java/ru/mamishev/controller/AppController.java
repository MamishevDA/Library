package main.java.ru.mamishev.controller;

/**
 * Created by MamishevDA on 24.07.2016.
 */

import main.java.ru.mamishev.api.AuthUser;
import main.java.ru.mamishev.model.BookImpl;
import main.java.ru.mamishev.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    UserImpl user;
    @Autowired
    BookImpl book;
    @Autowired
    AuthUser authUser;


    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("fromUser", user.getUser(1));
        return "books";

    }

    @RequestMapping(value = "/index**", method = {RequestMethod.GET})
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "library");
        model.addObject("message", "Welcome Page!");
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/books**", method = {RequestMethod.GET})
    public ModelAndView booksPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "list of books");
        model.addObject("currentUser", authUser.getCurrentUser());
        model.addObject("books", book.getPagedBookListWithUsers(0, 5, authUser.getCurrentUser().getName()));
        model.setViewName("books");
        return model;
    }

    @RequestMapping(value = "/users**", method = {RequestMethod.GET})
    public ModelAndView usersPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "list of users");
        model.addObject("users", user.getUserLists());
        model.addObject("currentUser", authUser.getCurrentUser());
        model.setViewName("users");
        return model;
    }

    @RequestMapping(value = "/userAlreadyExist**", method = {RequestMethod.GET})
    public ModelAndView userAlreadyExist(@RequestParam String login) {
        ModelAndView model = new ModelAndView();
        model.addObject("login", login);
        model.setViewName("userAlreadyExist");
        return model;

    }

    @RequestMapping(value = "/addUser**", method = {RequestMethod.POST})
    public String addUser(@RequestParam int id, @RequestParam String login, @RequestParam String pwd) {

        if (id != 0 && !"".equals(login)) {
            user.updateUser(login, pwd);
        } else if (id == 0 && !"".equals(login)) {
            user.create(login, pwd);
        } else {
            return "redirect:/labelNotSetMessage?message=login field can not be empty!";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/deleteUser**", method = {RequestMethod.GET})
    public String deleteUser(@RequestParam String name) {
        if (user.isUserInUse(name) > 0) {
            return "redirect:/labelNotSetMessage?message=User " + name + " take some books from library, Can't delete!";
        } else {
            user.delete(name);
            return "redirect:/users";
        }

    }

    @RequestMapping(value = "/addBook**", method = {RequestMethod.POST})
    public String addNewBook(@RequestParam int id, @RequestParam String isn, @RequestParam String author, @RequestParam String title) {

        if (id != 0) {
            book.updateBook(id, isn, author, title);
        } else if (id == 0 && !"".equals(isn) && !"".equals(author) && !"".equals(title)) {
            book.create(isn, author, title);
        } else {
            return "redirect:/labelNotSetMessage?message=" +
                    "All fields must be filled!";
        }
        return "redirect:/books";

    }

    @RequestMapping(value = "/bookAlreadyExist**", method = {RequestMethod.GET})
    public ModelAndView bookAlreadyExist(@RequestParam String isn) {
        ModelAndView model = new ModelAndView();
        model.addObject("isn", isn);
        model.setViewName("bookAlreadyExist");
        return model;

    }

    @RequestMapping(value = "/labelNotSetMessage**", method = {RequestMethod.GET})
    public ModelAndView labelNotSetMessage(@RequestParam String message) {
        ModelAndView model = new ModelAndView();
        model.addObject("message", message);
        model.setViewName("labelNotSetMessage");
        return model;

    }

    @RequestMapping(value = "/deleteBook**", method = {RequestMethod.GET})
    public String deleteBook(@RequestParam String isn) {
        book.delete(isn);
        //return this.booksPage();
        return "redirect:/books";

    }

    @RequestMapping(value = "/book**", method = {RequestMethod.GET})
    public ModelAndView addNewBook(@RequestParam String isn) {
        ModelAndView model = new ModelAndView();
        model.addObject(book.getBook(isn));

        return model;
    }

    @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
        model.addObject("message", "This is confidential page - Need Super Admin Role!");
        model.setViewName("protected");
        return model;

    }

    /**/
    @RequestMapping("/fillBookParameter")
    public String fillBookParameter(Model model) {
        return "fillBookParameter";

    }

}
