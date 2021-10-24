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

    @GetMapping
    public List<BookAd> getAllBookAd(){
        return bookAdService.getAllBookAd();
    }

    @GetMapping("/{id}")
    public BookAd getBookAd(@PathVariable long id){
        return bookAdService.getBookAd(id);
    }

    @PutMapping
    public void makeBookAd(Principal principal, @RequestBody BookAdData bookAdData){
        bookAdService.makeBookAd(principal.getName(), bookAdData);
    }

    @DeleteMapping("/{id}")
    public void deleteBookAd(Principal principal, @PathVariable long id){
        bookAdService.deleteBookAd(principal.getName(), id);
    }

}
