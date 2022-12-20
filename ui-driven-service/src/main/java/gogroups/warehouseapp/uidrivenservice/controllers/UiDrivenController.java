package gogroups.warehouseapp.uidrivenservice.controllers;

import gogroups.warehouseapp.uidrivenservice.responses.CategoryResponse;
import gogroups.warehouseapp.uidrivenservice.responses.ItemResponse;
import gogroups.warehouseapp.uidrivenservice.responses.ItemWithStoreResponse;
import gogroups.warehouseapp.uidrivenservice.services.UiDrivenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ui")
public class UiDrivenController {

    @Autowired
    private UiDrivenService uiDrivenService;

    @GetMapping("/categories/{storeId}")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesInAStore (
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long storeId
    ){
        return new  ResponseEntity<>(uiDrivenService.getAllCategoriesInAStore(userId, role, storeId), HttpStatus.OK);

    }

    @GetMapping("/items/{categoryId}")
    public ResponseEntity<List<ItemResponse>> getAllItemsOfAGivenCategory (
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable Long categoryId
    ){
        return new  ResponseEntity<>(uiDrivenService.getAllItemsOfAGivenCategory(userId, role, categoryId), HttpStatus.OK);

    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> getAllItems (
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role
    ){
        return new  ResponseEntity<>(uiDrivenService.getAllItemsInAUsersStores(userId, role), HttpStatus.OK);

    }

    @GetMapping("/items/search/{itemName}")
    public ResponseEntity<List<ItemWithStoreResponse>> getAllItemsWithThatName (
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @PathVariable String itemName
    ){
        return new  ResponseEntity<>(uiDrivenService.getAllItemsWithThatName(userId, role, itemName), HttpStatus.OK);

    }
}
