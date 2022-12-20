package gogroups.warehouseapp.uidrivenservice.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gogroups.warehouseapp.uidrivenservice.models.Category;
import gogroups.warehouseapp.uidrivenservice.models.Item;
import gogroups.warehouseapp.uidrivenservice.repos.ItemRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    private int quantity;
    private double sellingPrice;
    private double costPrice;

    public Category getCategory(ItemRepo itemRepo){
        Item item = itemRepo.findById(this.id).get();
        return item.getCategory();
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", quantity=" + quantity +
                ", sellingPrice=" + sellingPrice +
                ", costPrice=" + costPrice +
                '}';
    }
}
