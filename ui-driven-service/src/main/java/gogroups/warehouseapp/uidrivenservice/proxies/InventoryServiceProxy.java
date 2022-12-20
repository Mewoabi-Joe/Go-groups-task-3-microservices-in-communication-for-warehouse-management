package gogroups.warehouseapp.uidrivenservice.proxies;

import gogroups.warehouseapp.uidrivenservice.responses.ItemResponse;
import gogroups.warehouseapp.uidrivenservice.responses.StoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceProxy {
    @GetMapping("inventory/stores")
    public List<StoreResponse> getAllAUsersStores(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role
    );

    @GetMapping("inventory/items/{storeId}")
    public List<ItemResponse> getItemsInStore(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId
    );

}
