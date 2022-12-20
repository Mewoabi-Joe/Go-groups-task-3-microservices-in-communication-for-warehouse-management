package gogroups.warehouseapp.uidrivenservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String createdOn;
    private String Location;
    private int phoneNumber;
}
