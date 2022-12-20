package gogroups.warehouseapp.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    @NotBlank @Size(min = 3)
    private String name;
    private String description;
    private String imageUrl;
    private String Location;
    @Min(value = 600000000, message = "Phone number should start with 6 and be followed by 8 figures")
    @Max(value = 700000000, message = "Phone number should start with 6 and be followed by 8 figures")
    private int phoneNumber;
}
