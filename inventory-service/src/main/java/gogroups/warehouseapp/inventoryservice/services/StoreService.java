package gogroups.warehouseapp.inventoryservice.services;


import gogroups.warehouseapp.inventoryservice.dto.CategoryDto;
import gogroups.warehouseapp.inventoryservice.dto.ItemDto;
import gogroups.warehouseapp.inventoryservice.dto.StoreDto;
import gogroups.warehouseapp.inventoryservice.exceptions.BadRequestException;
import gogroups.warehouseapp.inventoryservice.exceptions.NotFoundException;
import gogroups.warehouseapp.inventoryservice.models.Category;
import gogroups.warehouseapp.inventoryservice.models.Item;
import gogroups.warehouseapp.inventoryservice.models.Store;
import gogroups.warehouseapp.inventoryservice.models.User;
import gogroups.warehouseapp.inventoryservice.repos.CategoryRepo;
import gogroups.warehouseapp.inventoryservice.repos.ItemRepo;
import gogroups.warehouseapp.inventoryservice.repos.StoreRepo;
import gogroups.warehouseapp.inventoryservice.repos.UserRepo;
import gogroups.warehouseapp.inventoryservice.responses.CategoryResponse;
import gogroups.warehouseapp.inventoryservice.responses.ItemResponse;
import gogroups.warehouseapp.inventoryservice.responses.StoreResponse;
import gogroups.warehouseapp.inventoryservice.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StoreService {
    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    private String secret = "This secret should be stored securedly in production";


    public StoreResponse createStore(Long userId, StoreDto storeDto) {
        Optional<Store> optionalStore = storeRepo.findByName(storeDto.getName());
        if(optionalStore.isPresent()) throw new BadRequestException("A store with that name already exist");
        Optional<User> optionalUser = userRepo.findById(userId);
        if(!optionalUser.isPresent()) throw new NotFoundException("No user exist with that Id");
        Store savedStore = storeRepo.save(new Store(storeDto.getName(), storeDto.getDescription(), storeDto.getImageUrl(), storeDto.getLocation(), storeDto.getPhoneNumber(), optionalUser.get()));
        return new StoreResponse(savedStore.getId(), savedStore.getName(), savedStore.getDescription(), savedStore.getImageUrl(), savedStore.getCreatedOn(), savedStore.getLocation(), savedStore.getPhoneNumber());
    }

    public List<StoreResponse> getAllAUsersStores(Long userId, String role) {
        if(!Utility.isValidRole(role)) throw new BadRequestException("That role is not valid");
        User user = userService.getUserWithIdIfExistOrError(userId);
        List<Store> stores = user.getStores();
        return stores.stream().map(store -> new StoreResponse(store.getId(), store.getName(), store.getDescription(),store.getImageUrl(),store.getCreatedOn(), store.getLocation(), store.getPhoneNumber())).collect(Collectors.toList());
    }

    public ItemResponse createAddItemInStore(Long userId, String role, Long storeId, ItemDto itemDto) {
        Category category = getCategoryIfExistOrThrowError(itemDto.getCategoryName());
        Store store = getStoreWithIdIfExistOrThrowError(storeId);
        Item item = itemRepo.save(new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getImageUrl(), itemDto.getQuantity(), itemDto.getSellingPrice(), itemDto.getCostPrice(), category));
        store.getItems().add(item);
        storeRepo.save(store);
        return new ItemResponse(item.getId(),item.getName(), item.getDescription(),item.getImageUrl(), LocalDateTime.now(), item.getQuantity(),item.getSellingPrice(), itemDto.getCostPrice());
    }

    public Category getCategoryIfExistOrThrowError(String categoryName){
        Optional<Category> optionalCategory = categoryRepo.findByName(categoryName);
        if(optionalCategory.isPresent()) return optionalCategory.get();
        throw new NotFoundException("No category exist with that name, consider creating one");
    }

    public Store getStoreWithIdIfExistOrThrowError(Long storeId){
        Optional<Store> optionalStore = storeRepo.findById(storeId);
        if(optionalStore.isPresent()) return optionalStore.get();
        throw new NotFoundException("No store exist with that id");
    }

    public CategoryResponse createCategory(Long userId, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findByName(categoryDto.getName());
        if(optionalCategory.isPresent()) throw new BadRequestException("A category with that name already exist, you can add items to it");
        Category category = categoryRepo.save(new Category(categoryDto.getName(), categoryDto.getDescription()));
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription(), category.getImageUrl(), category.getCreatedOn());
    }

    public StoreResponse getStoreWithId(Long storeId) {
        Optional<Store> optionalStore = storeRepo.findById(storeId);
        if(!optionalStore.isPresent()) throw new NotFoundException("No store exist with that id");
        Store store = optionalStore.get();
        return new StoreResponse(store.getId(), store.getName(), store.getDescription(), store.getImageUrl(),store.getCreatedOn(), store.getLocation(),store.getPhoneNumber());
    }

    public List<ItemResponse> getItemsInStore(Long storeId) {
        Store store = this.getStoreWithIdIfExistOrThrowError(storeId);
        List<Item> itemsInStore = store.getItems();
        return itemsInStore.stream().map(item -> new ItemResponse(item.getId(), item.getName(),item.getDescription(), item.getImageUrl(),LocalDateTime.now(),  item.getQuantity(), item.getSellingPrice(), item.getCostPrice())).collect(Collectors.toList());
    }
}
