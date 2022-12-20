package gogroups.warehouseapp.uidrivenservice.responses;

import gogroups.warehouseapp.uidrivenservice.models.Category;
import gogroups.warehouseapp.uidrivenservice.models.Item;
import gogroups.warehouseapp.uidrivenservice.repos.ItemRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemWithStoreResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    private Long storeId;
    private String storeName;

    public Category getCategory(ItemRepo itemRepo){
        Item item = itemRepo.findById(this.id).get();
        return item.getCategory();
    }


}
