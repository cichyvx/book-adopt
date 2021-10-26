package book.adopt.offer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "offer")
@Table(name = "offer")
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_ad_id")
    private long bookAdId;

    @Column(name = "user_id")
    private long userId;

    /**
     * book for exchange
     * IT'S NOT BOOK FROM AD!!!
     */
    @Column(name = "book_id")
    private long bookId;

}
