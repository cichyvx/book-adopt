package book.adopt.bookCase;

import book.adopt.book.Book;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/bookCase")
@AllArgsConstructor
public class BookCaseController {

    private final BookCaseService bookCaseService;

    /**
     *
     * @param principal current logged user
     * @return list of all book in bookcase from user account
     */
    @GetMapping
    public Set<Book> getMyBook(Principal principal){
        return bookCaseService.getUserBook(principal.getName());
    }

    /**
     *
     * @param principal current logged user
     * @param bookId book id you search for
     * @return one book with specified id
     */
    @GetMapping("/{bookId}")
    public Book getBookFromUser(Principal principal, @PathVariable long bookId){
        return bookCaseService.getBookFromUser(principal.getName(), bookId);
    }

    /**
     * adding new book to bookcase
     * @param principal current logged user
     * @param bookId book you want to add to bookcase
     */
    @PutMapping("/{bookId}")
    public void addBookToUser(Principal principal, @PathVariable long bookId){
        bookCaseService.addBookToUser(principal.getName(), bookId);
    }

    /**
     * removing book from bookcase
     * @param principal current logged user
     * @param bookId book to deleted from bookcase
     */
    @DeleteMapping("/{bookId}")
    public void deleteBookFromUser(Principal principal, @PathVariable long bookId){
        bookCaseService.deleteBookFromUser(principal.getName(), bookId);
    }

}
