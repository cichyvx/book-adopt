package book.adopt.bookAd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookAdRepository extends JpaRepository<BookAd, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM bookAd b WHERE b.id = ?1")
    void deleteBookAd(long id);

    BookAd getByBookIdAndUserId(long bookId, long userId);
}
