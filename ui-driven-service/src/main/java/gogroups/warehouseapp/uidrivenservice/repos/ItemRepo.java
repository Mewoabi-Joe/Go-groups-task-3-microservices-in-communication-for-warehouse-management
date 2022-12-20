package gogroups.warehouseapp.uidrivenservice.repos;

import gogroups.warehouseapp.uidrivenservice.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
//    Optional<Item> findByName(String name);
}
