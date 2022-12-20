package gogroups.warehouseapp.inventoryservice.models;

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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @ManyToMany(mappedBy = "items")
    private List<Store> stores = new ArrayList<>();

    public Item(String name, String description, String imageUrl, int quantity, double sellingPrice, double costPrice, Category category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdOn = LocalDateTime.now();
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.category = category;
    }
}
