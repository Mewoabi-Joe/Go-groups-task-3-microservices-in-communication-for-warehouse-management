package gogroups.warehouseapp.uidrivenservice.repos;

import gogroups.warehouseapp.uidrivenservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
