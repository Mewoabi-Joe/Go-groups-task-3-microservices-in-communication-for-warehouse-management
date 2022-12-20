package gogroups.warehouseapp.userservice.repos;

import gogroups.warehouseapp.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByNameOrEmail(String name, String email);

    Optional<User> findByEmail(String email);
}
