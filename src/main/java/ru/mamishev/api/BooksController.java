package main.java.ru.mamishev.api;

import main.java.ru.mamishev.model.Book;
import main.java.ru.mamishev.model.BookImpl;
import main.java.ru.mamishev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MamishevDA on 26.07.2016.
 */
@RestController
public class BooksController {

    @Autowired
    BookImpl bookImpl;
    @Autowired
    AuthUser authUser;

    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestParam int startIndex) {
        return bookImpl.getPagedBookListWithUsers(startIndex, 5, authUser.getCurrentUser().getName());
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam String isn) {
        return bookImpl.getBook(isn);
    }

    @RequestMapping("/takeBookForUser")
    public void takeBookForUser(@RequestParam String isn) {
        Book book = bookImpl.getBook(isn);
        User currentUser = authUser.getCurrentUser();
        //вернем книгу
        if (currentUser.getId() == book.getTakedbyuser()) {
            bookImpl.updateTakedByUser(isn, null);

        } else if (book.getTakedbyuser() == 0) {
            bookImpl.updateTakedByUser(isn, currentUser.getId());
        }


    }
}
