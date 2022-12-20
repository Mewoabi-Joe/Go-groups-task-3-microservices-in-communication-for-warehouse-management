package gogroups.warehouseapp.inventoryservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    private String Location;
    private int phoneNumber;
}
