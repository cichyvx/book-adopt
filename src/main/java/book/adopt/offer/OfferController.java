package book.adopt.offer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/offer")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    /**
     *
     * @param principal
     * @return all offers you sent
     */
    @GetMapping("/send")
    public List<Offer> mySendOffer(Principal principal){
        return offerService.getSendOfferFromUser(principal.getName());
    }

    /**
     *
     * @param principal
     * @return all offers you received
     */
    @GetMapping("/received")
    public List<Offer> myReceivedOffer(Principal principal){
        return offerService.getReceivedOfferFromUser(principal.getName());
    }

    /**
     * making offer
     * @param principal
     * @param bookAdId book ad that you want to offer
     */
    // TODO: 24.10.2021 ADD EXCHANGE POSSIBILITY
    @PutMapping("/{bookAdId}")
    public void makeOffer(Principal principal, @PathVariable long bookAdId ){
        offerService.makeOffer(principal.getName(), bookAdId, null);
    }

    /**
     * rejecting someone offer
     * @param principal
     * @param offerId offer you want to reject
     */
    @DeleteMapping("/reject/{offerId}")
    public void rejectOffer(Principal principal, @PathVariable long offerId){
        offerService.rejectOffer(principal.getName(), offerId);
    }

    /**
     * accepting someone offer
     * @param principal
     * @param offerId offer you want to accept
     */
    @PostMapping("/accept/{offerId}")
    public void acceptOffer(Principal principal, @PathVariable long offerId){
        offerService.acceptOffer(principal.getName(), offerId);
    }

}
