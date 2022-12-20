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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    @OneToMany(mappedBy = "category")
    List<Item> items = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.createdOn = LocalDateTime.now();
        this.description = description;
    }
}
