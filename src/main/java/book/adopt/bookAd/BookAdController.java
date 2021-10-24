package book.adopt.bookAd;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bookAd")
@AllArgsConstructor
public class BookAdController {

    private final BookAdService bookAdService;

    /**
     *
     * @return list of all BookAd
     */
    @GetMapping
    public List<BookAd> getAllBookAd(){
        return bookAdService.getAllBookAd();
    }

    /**
     *
     * @param id book id
     * @return book with id from param
     */
    @GetMapping("/{id}")
    public BookAd getBookAd(@PathVariable long id){
        return bookAdService.getBookAd(id);
    }

    /**
     * creating new BookAd
     * @param principal owner of BookAd
     * @param bookAdData information about BookAd
     */
    @PutMapping
    public void makeBookAd(Principal principal, @RequestBody BookAdData bookAdData){
        bookAdService.makeBookAd(principal.getName(), bookAdData);
    }

    /**
     * deleting bookAd with id from param
     * @param principal BookAd owner
     * @param id of bookAd
     */
    @DeleteMapping("/{id}")
    public void deleteBookAd(Principal principal, @PathVariable long id){
        bookAdService.deleteBookAd(principal.getName(), id);
    }

}
