package book.adopt.book;

import book.adopt.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "book")
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String author;
    private String description;

   @JsonIgnoreProperties("books")
   @ManyToMany(mappedBy = "books", targetEntity = User.class)
   private Set<User> users;

}
