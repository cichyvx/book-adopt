package book.adopt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE user u SET u.username = ?2, u.password = ?3 WHERE u.id = ?1")
    void updateUser(long id, String username, String password);
}
