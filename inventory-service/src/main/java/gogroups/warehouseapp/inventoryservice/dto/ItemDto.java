package gogroups.warehouseapp.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    @NotBlank @Size(min = 3)
    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    private String categoryName;
}
