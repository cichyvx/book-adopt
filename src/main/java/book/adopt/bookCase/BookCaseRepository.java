package book.adopt.bookCase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookCaseRepository extends JpaRepository<BookCase, Long> {

    /**
     * adding new bookcase relation
     * @param userId
     * @param bookId
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM bookCase b WHERE b.userId = ?1 and b.bookId = ?2")
    void deleteBook(long userId, long bookId);

    @Transactional
    @Modifying
    void deleteAllByBookId(long bookId);
}
