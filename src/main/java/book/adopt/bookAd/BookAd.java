package book.adopt.bookAd;

import book.adopt.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "bookAd")
@Table(name = "book_ad")
@Getter
@Setter
public class BookAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "added_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    private String description;

    @Column(name = "to_exchange")
    private byte toExchange;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User owner;

    public boolean toExchange(){
        return toExchange != 0;
    }

    public void setToExchange(boolean b){
        toExchange = b? (byte) 1 : (byte) 0;
    }

}
