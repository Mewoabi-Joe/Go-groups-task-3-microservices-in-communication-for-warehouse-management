package gogroups.warehouseapp.inventoryservice.repos;

import gogroups.warehouseapp.inventoryservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByNameOrEmail(String name, String email);
}
