package book.adopt.offer;

import book.adopt.bookAd.BookAd;
import book.adopt.bookAd.BookAdRepository;
import book.adopt.bookCase.BookCase;
import book.adopt.bookCase.BookCaseRepository;
import book.adopt.user.User;
import book.adopt.user.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final BookAdRepository bookAdRepository;
    private final BookCaseRepository bookCaseRepository;

    /**
     *
     * @param username
     * @return list of all offer sent by user from param
     */
    public List<Offer> getSendOfferFromUser(@NotNull String username) {
        return offerRepository.getByUserId(userRepository.getUserByUsername(username).orElseThrow().getId());
    }

    /**
     *
     * @param username
     * @return list of all offer received by user from param
     */
    public List<Offer> getReceivedOfferFromUser(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();

        List<Offer> list = new java.util.ArrayList<>(Collections.emptyList());

        for(BookAd b : user.getBooksAd()){
            list.addAll(offerRepository.getByBookAdId(b.getId()));
        }

        return list;
    }

    /**
     * creating new offer record
     * @param username user making offer
     * @param bookAdId id from book ad you want to offer
     * @param bookId book that you want to exchange
     * @throws IllegalAccessError if user making offer for himself book ad
     * @throws NullPointerException if auction require something for exchange, and you not put nothing in your offer
     */
    public void makeOffer(@NotNull String username, @NotNull long bookAdId, Long bookId) {
        User user = userRepository.getUserByUsername(username).orElseThrow();
        BookAd bookAd = bookAdRepository.findById(bookAdId).orElseThrow();

        if (user.getBooksAd().contains(bookAd)){
            throw new IllegalAccessError("you cannot offer to your ad");
        }

        if(bookAd.toExchange() && bookId == null){
            throw new NullPointerException("this auction required something for exchange");
        }

        Offer offer = new Offer();
        offer.setUserId(user.getId());
        offer.setBookAdId(bookAdId);
        if(bookAd.toExchange())
            offer.setBookId(bookId);

        offerRepository.save(offer);
    }

    /**
     * this method will delete offer with id given in the parameter
     * @param username of user owned bookAd
     * @param offerId id you want to reject
     * @throws IllegalAccessError if you want reject offer that has benn not sent to you
     */
    public void rejectOffer(String username, long offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        User user = userRepository.getUserByUsername(username).orElseThrow();

        boolean isOwner = false;

        for (BookAd b : user.getBooksAd()){
            if(b.getId() == offer.getBookAdId()){
                isOwner = true;
                break;
            }
        }
        if(!isOwner)
            throw new IllegalAccessError();

        offerRepository.deleteOffer(offerId);

    }

    /**
     * this method will change the owner of book from current owner to user
     * that make offer that you accepted.
     * all offer for this bookAD and the bookAd will be removed
     * @param username of user owned book ad
     * @param offerId id you want to accept
     * @throws IllegalAccessError if you want accept offer that has benn not sent to you
     */
    public void acceptOffer(String username, long offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        User user = userRepository.getUserByUsername(username).orElseThrow();
        BookAd bookAd = bookAdRepository.findById(offer.getBookAdId()).orElseThrow();

        boolean isOwner = false;

        for (BookAd b : user.getBooksAd()){
            if(b.getId() == offer.getBookAdId()){
                isOwner = true;
                break;
            }
        }
        if(!isOwner)
            throw new IllegalAccessError("access denied");

        BookCase bookCase = new BookCase();
        bookCase.setUserId(offer.getUserId());
        bookCase.setBookId(bookAd.getBookId());

        offerRepository.deleteOffer(bookAd.getId());
        bookAdRepository.deleteBookAd(bookAd.getId());
        bookCaseRepository.deleteBook(user.getId(), bookAd.getBookId());
        bookCaseRepository.save(bookCase);
    }
}