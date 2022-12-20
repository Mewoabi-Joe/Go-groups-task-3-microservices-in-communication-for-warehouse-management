package gogroups.warehouseapp.uidrivenservice.repos;

import gogroups.warehouseapp.uidrivenservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
}
