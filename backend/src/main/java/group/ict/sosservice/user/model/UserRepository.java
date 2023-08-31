package group.ict.sosservice.user.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(final Email email);

    boolean existsUserByEmail(final Email email);
}
