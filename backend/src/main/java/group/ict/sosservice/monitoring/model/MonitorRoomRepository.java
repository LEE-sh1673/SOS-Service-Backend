package group.ict.sosservice.monitoring.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group.ict.sosservice.user.model.User;

public interface MonitorRoomRepository extends JpaRepository<MonitorRoom, Long> {

    @Query("SELECT m FROM MonitorRoom m join fetch m.user WHERE m.user = :user")
    Optional<MonitorRoom> findByUser(@Param("user") final User user);

    boolean existsMonitorRoomByUser(final User user);
}
