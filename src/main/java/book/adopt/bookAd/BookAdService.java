package book.adopt.bookAd;

import book.adopt.book.Book;
import book.adopt.book.BookRepository;
import book.adopt.user.User;
import book.adopt.user.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookAdService {

    private final BookAdRepository bookAdRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    /**
     *
     * @return list of all existing bookAD
     */
    public List<BookAd> getAllBookAd() {
        return bookAdRepository.findAll();
    }

    /**
     *
     * @param id
     * @return bookAd with id from param
     * @throws java.util.NoSuchElementException if any of book don't have this id
     */
    public BookAd getBookAd(@NotNull long id) {
        return bookAdRepository.findById(id).orElseThrow();
    }

    /**
     * making new BookAd
     * bookAd must be unique, user can't make two announcements for one book
     * @param username of owner
     * @param bookAdData information about bookAd
     * @throws Exception if for this book already existing announcement for this user
     */
    @SneakyThrows
    public void makeBookAd(@NotNull String username, @NotNull BookAdData bookAdData) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        Book book = bookRepository.getById(bookAdData.getBookId());

        assert book != null;

        if(bookAdRepository.findByBookIdAndUserId(bookAdData.getBookId(), user.getId()).isPresent()){
            throw new Exception("book ad already exist");
        }

        if(!user.getBooks().contains(book)){
            throw new IllegalArgumentException("you don't have this book in your bookcase");
        }

        BookAd bookAd = new BookAd();
        bookAd.setAddedDate(new Date());
        bookAd.setDescription(bookAdData.getDescription());
        bookAd.setToExchange(bookAdData.isToExchange());
        bookAd.setUserId(user.getId());
        bookAd.setBookId(bookAdData.getBookId());

        bookAdRepository.save(bookAd);
    }

    /**
     * delete bookAd with specified in in param
     * @param username of owner
     * @param id of bookId
     * @throws BadCredentialsException if someone is trying to delete an ad that does not belong to him
     */
    public void deleteBookAd(@NotNull String username, @NotNull long id) {
        BookAd bookAd = bookAdRepository.getById(id);

        assert bookAd != null;

        if(!bookAd.getOwner().getUsername().equals(username)){
            throw new BadCredentialsException("");
        }

            bookAdRepository.deleteBookAd(id);

    }
}
