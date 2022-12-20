package gogroups.warehouseapp.uidrivenservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    private String Location;
    private int phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToMany
    @JoinTable(
            name="store_item",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    List<Item> items = new ArrayList<>();

    public Store( String name, String description, String imageUrl, String location, int phoneNumber, User user) {
        this.name = name;
        this.description = description;
        this.createdOn = LocalDateTime.now();
        this.imageUrl = imageUrl;
        Location = location;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
