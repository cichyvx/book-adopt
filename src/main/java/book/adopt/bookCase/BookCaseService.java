package book.adopt.bookCase;

import book.adopt.book.Book;
import book.adopt.book.BookRepository;
import book.adopt.user.User;
import book.adopt.user.UserRepository;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class BookCaseService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookCaseRepository bookCaseRepository;

    /**
     * get all user books
     * @param username
     * @return list of all owned book from user with specified username
     */
    public Set<Book> getUserBook(@NotNull String username) {
        return userRepository.getUserByUsername(username).orElseThrow().getBooks();
    }

    /**
     * add book to bookcase
     * @param username
     * @param bookId book id to be added
     */
    public void addBookToUser(@NotNull String username,@NotNull long bookId){
        User user = userRepository.getUserByUsername(username).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        BookCase bookCase = new BookCase();
        bookCase.setUserId(user.getId());
        bookCase.setBookId(book.getId());

        bookCaseRepository.save(bookCase);
    }

    /**
     * delete book from bookcase
     * @param username
     * @param bookId book to be deleted
     */
    public void deleteBookFromUser(@NotNull String username, @NotNull long bookId) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        bookCaseRepository.deleteBook(user.getId(), book.getId());
    }

    /**
     * get one book from bookcase
     * @param username
     * @param bookId book id that we want to find
     * @return book with specified id
     * @throws NotFoundException if any book in bookcase do not match
     */
    @SneakyThrows
    public Book getBookFromUser(@NotNull String username, @NotNull long bookId) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        for(Book b : user.getBooks()){
            if(b.getId() == bookId)
                return b;
        }
        throw new NotFoundException("Book not found in user books");
    }
}
