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

    public List<BookAd> getAllBookAd() {
        return bookAdRepository.findAll();
    }

    public BookAd getBookAd(@NotNull long id) {
        return bookAdRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    public void makeBookAd(@NotNull String username, @NotNull BookAdData bookAdData) {
        User user = userRepository.getUserByUsername(username);
        Book book = bookRepository.getById(bookAdData.getBookId());

        assert user != null || book != null;

        if(bookAdRepository.getByBookIdAndUserId(bookAdData.getBookId(), user.getId()) != null){
            throw new Exception("book ad already exist");
        }

        BookAd bookAd = new BookAd();
        bookAd.setAddedDate(new Date());
        bookAd.setDescription(bookAdData.getDescription());
        bookAd.setToExchange(bookAdData.isToExchange());
        bookAd.setUserId(user.getId());
        bookAd.setBookId(bookAdData.getBookId());

        bookAdRepository.save(bookAd);
    }

    public void deleteBookAd(@NotNull String username, @NotNull long id) {
        BookAd bookAd = bookAdRepository.getById(id);

        assert bookAd != null;

        if(!bookAd.getOwner().getUsername().equals(username)){
            throw new BadCredentialsException("");
        }

            bookAdRepository.deleteBookAd(id);

    }
}
