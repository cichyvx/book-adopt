package book.adopt.bookCase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "bookCase")
@Table(name = "book_case")
@Getter
@Setter
public class BookCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "book_id")
    private long bookId;
}
