package gogroups.warehouseapp.inventoryservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
