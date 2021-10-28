package book.adopt.book;

import book.adopt.bookAd.BookAd;
import book.adopt.bookAd.BookAdRepository;
import book.adopt.bookCase.BookCaseRepository;
import book.adopt.offer.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookCaseRepository bookCaseRepository;
    private final BookAdRepository bookAdRepository;
    private final OfferRepository offerRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBook(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> getBookByName(String name) {
        return bookRepository.findBookByName(name);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
        bookCaseRepository.deleteAllByBookId(id);
        List<BookAd> bookAdList = bookAdRepository.findAllByBookId(id);
        for(BookAd b : bookAdList)
            offerRepository.deleteOffer(b.getId());
        bookAdRepository.deleteAllByBookId(id);
    }

    public void updateBook(long id, BookData bookData) {
        bookRepository.updateBook(bookData.getName(), bookData.getAuthor(), bookData.getDescription(), id);
    }

    public void addBook(BookData bookData) {
        Book book = new Book();
        List<Book> mayBeBook = bookRepository.findBookByAuthor(bookData.getAuthor());
        mayBeBook.addAll(bookRepository.findBookByName(bookData.getName()));

        for(Book b : mayBeBook){
            if(b.getAuthor().equalsIgnoreCase(bookData.getAuthor()) &&
                    b.getName().equalsIgnoreCase(bookData.getName()))

                throw new BadCredentialsException("this book already exist");

        }

        book.setAuthor(bookData.getAuthor());
        book.setName(bookData.getName());
        if(bookData.getDescription() != null)
            book.setDescription(bookData.getDescription());
        else
            book.setDescription("no description");
        bookRepository.save(book);
    }
}
