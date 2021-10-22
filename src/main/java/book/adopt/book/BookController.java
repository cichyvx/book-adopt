package book.adopt.book;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBook(){
        return bookService.getAllBook();
    }

    @PutMapping
    public void addBook(@RequestBody BookData bookData){
        bookService.addBook(bookData);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.getBook(id);
    }

    @PatchMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody BookData bookData){
        bookService.updateBook(id, bookData);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }

    @GetMapping("/author/{author}")
    public List<Book> getAllByAuthor(@PathVariable String author){
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("/name/{name}")
    public List<Book> getAllByName(@PathVariable String name){
        return bookService.getBookByName(name);
    }

}
