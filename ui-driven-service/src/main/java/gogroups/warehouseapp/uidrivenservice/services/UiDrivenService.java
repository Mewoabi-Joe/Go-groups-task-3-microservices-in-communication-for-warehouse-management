package gogroups.warehouseapp.uidrivenservice.services;

import gogroups.warehouseapp.uidrivenservice.proxies.InventoryServiceProxy;
import gogroups.warehouseapp.uidrivenservice.repos.ItemRepo;
import gogroups.warehouseapp.uidrivenservice.responses.CategoryResponse;
import gogroups.warehouseapp.uidrivenservice.responses.ItemResponse;
import gogroups.warehouseapp.uidrivenservice.responses.ItemWithStoreResponse;
import gogroups.warehouseapp.uidrivenservice.responses.StoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiDrivenService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private InventoryServiceProxy inventoryServiceProxy;

    public List<CategoryResponse> getAllCategoriesInAStore(Long userId, String role, Long storeId) {
        List<ItemResponse> itemsR = inventoryServiceProxy.getItemsInStore(userId, role, storeId);

        List<CategoryResponse> categoriesR = new ArrayList<>();
        itemsR.forEach(itemR -> {
            if(!categoriesHasItemCategory(categoriesR, itemR.getCategory(itemRepo).getName())){
                categoriesR.add(new CategoryResponse(itemR.getCategory(itemRepo).getId(), itemR.getCategory(itemRepo).getName(), itemR.getCategory(itemRepo).getDescription(), itemR.getCategory(itemRepo).getImageUrl(),itemR.getCategory(itemRepo).getCreatedOn() ));
            }
        });

        return categoriesR;
    }

    private boolean categoriesHasItemCategory(List<CategoryResponse> categoriesR, String itemCategory) {
        return categoriesR.stream().anyMatch(categoryR -> categoryR.getName().equals(itemCategory));
    }

    public List<ItemResponse> getAllItemsOfAGivenCategory(Long userId, String role, Long categoryId) {

        List<ItemResponse> allItemsR = this.getAllItemsInAUsersStores(userId, role);

        List<ItemResponse> itemsToReturn = allItemsR.stream().filter(itemR -> ( itemR.getCategory(itemRepo).getId() == (categoryId))).collect(Collectors.toList());
        return itemsToReturn;
    }

    public List<ItemWithStoreResponse> getAllItemsWithThatName(Long userId, String role, String itemName) {
        List<ItemWithStoreResponse> allItemsWithStoreR = this.getAllItemsInAUsersStoreWithStoreInfo(userId, role);
        List<ItemWithStoreResponse> itemsWithStoreToReturn = allItemsWithStoreR.stream().filter(itemR -> ( itemR.getName().contains(itemName))).collect(Collectors.toList());
        return itemsWithStoreToReturn;
    }

    public List<ItemResponse> getAllItemsInAUsersStores(Long userId, String role){
        List<StoreResponse> storesR = inventoryServiceProxy.getAllAUsersStores(userId, role);

        List<ItemResponse> allItemsR = new ArrayList<>();


        storesR.forEach(storeR  -> {
            List<ItemResponse> itemsR =  inventoryServiceProxy.getItemsInStore(userId, role, storeR.getId());
            allItemsR.addAll(itemsR);
        });

        return allItemsR;
    }

    public List<ItemWithStoreResponse> getAllItemsInAUsersStoreWithStoreInfo(Long userId, String role){
        List<StoreResponse> storesR = inventoryServiceProxy.getAllAUsersStores(userId, role);

        List<ItemWithStoreResponse> allItemsWithStoreR = new ArrayList<>();

        storesR.forEach(storeR  -> {
            List<ItemResponse> itemsR =  inventoryServiceProxy.getItemsInStore(userId, role, storeR.getId());
            List<ItemWithStoreResponse>  itemWithStoreR = itemsR.stream().map(itemR  -> new ItemWithStoreResponse(itemR.getId(), itemR.getName(), itemR.getDescription(), itemR.getImageUrl(), itemR.getQuantity(), itemR.getSellingPrice(), itemR.getCostPrice(), storeR.getId(), storeR.getName())).collect(Collectors.toList());
            allItemsWithStoreR.addAll(itemWithStoreR);
        });

        return allItemsWithStoreR;
    }

}
