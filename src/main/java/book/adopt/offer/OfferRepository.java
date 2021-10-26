package book.adopt.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> getByUserId(long id);
    List<Offer> getByBookAdId(long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM offer o WHERE o.bookAdId = ?1")
    void deleteOffer(long bookAdId);
}
