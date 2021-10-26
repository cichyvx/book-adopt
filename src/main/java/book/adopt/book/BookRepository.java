package book.adopt.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByAuthor(String author);
    List<Book> findBookByName(String name);

    Optional<Book> findById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE book b SET b.name = ?1, b.author = ?2, b.description = ?3 WHERE b.id = ?4")
    void updateBook(String name, String author, String description, long id);
}
