package gogroups.warehouseapp.inventoryservice.controllers;

import gogroups.warehouseapp.inventoryservice.dto.CategoryDto;
import gogroups.warehouseapp.inventoryservice.dto.ItemDto;
import gogroups.warehouseapp.inventoryservice.dto.StoreDto;
import gogroups.warehouseapp.inventoryservice.responses.CategoryResponse;
import gogroups.warehouseapp.inventoryservice.responses.ItemResponse;
import gogroups.warehouseapp.inventoryservice.responses.StoreResponse;
import gogroups.warehouseapp.inventoryservice.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Appart from the get to /stores and POST /stores I stop validating checking if there is a user with
// that Id or the role is valid since if the token was tampered with it would not validate at the api gateway.


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/stores")
    public ResponseEntity<StoreResponse> createStore(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @Valid @RequestBody StoreDto storeDto
    ) {
        return new ResponseEntity<>(storeService.createStore(userId, storeDto), HttpStatus.CREATED);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return new ResponseEntity<>(storeService.createCategory(userId, categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/items/{storeId}")
    public ResponseEntity<ItemResponse> createAddItemInStore(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        return new ResponseEntity<>(storeService.createAddItemInStore(userId, role, storeId, itemDto), HttpStatus.CREATED);
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StoreResponse>> getAllAUsersStores(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role
    ) {
        return new ResponseEntity<>(storeService.getAllAUsersStores(userId, role), HttpStatus.OK);
    }

    @GetMapping("/stores/{storeId}")
    public ResponseEntity<StoreResponse> getStoreWithId(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId
    ) {
        return new ResponseEntity<>(storeService.getStoreWithId(storeId), HttpStatus.OK);
    }

    @GetMapping("/items/{storeId}")
    public ResponseEntity<List<ItemResponse>> getItemsInStore(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId
    ) {
        return new ResponseEntity<>(storeService.getItemsInStore(storeId), HttpStatus.OK);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<List<ItemResponse>> update(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId
    ) {
        return new ResponseEntity<>(storeService.getItemsInStore(storeId), HttpStatus.OK);
    }

    // Still to implement

    @PutMapping("/items/{itemId}/updatequantity")
    public String updateItem() {
        return "true";
    }

    @DeleteMapping("/items/{itemId}")
    public String deleteItem() {
        return "true";
    }

    @PutMapping("/category/{categoryId}")
    public String updateCategory() {
        return "true";
    }

    @PutMapping("/stores/{storeId}")
    public String updatestore() {
        return "true";
    }

    @DeleteMapping("/stores/{storeId}")
    public String deleteStore() {
        return "true";
    }

    @DeleteMapping("/category/{categoryId}")
    public String deleteCategory() {
        return "true";
    }



}
