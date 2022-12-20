package gogroups.warehouseapp.inventoryservice.repos;

import gogroups.warehouseapp.inventoryservice.models.Category;
import gogroups.warehouseapp.inventoryservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
