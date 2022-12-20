package gogroups.warehouseapp.inventoryservice.repos;

import gogroups.warehouseapp.inventoryservice.models.Item;
import gogroups.warehouseapp.inventoryservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
//    Optional<Item> findByName(String name);
}
