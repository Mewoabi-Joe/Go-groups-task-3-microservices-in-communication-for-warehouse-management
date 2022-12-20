package gogroups.warehouseapp.inventoryservice.repos;

import gogroups.warehouseapp.inventoryservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
}
