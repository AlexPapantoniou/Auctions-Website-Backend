package gr.uoa.tedi.backend.repository;

import gr.uoa.tedi.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username <> 'admin'")
    List<User> findAllExcludingAdmin();

    void deleteById(Long id);
}