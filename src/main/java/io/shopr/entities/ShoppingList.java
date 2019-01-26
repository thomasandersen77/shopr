package io.shopr.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_timestamp",
            updatable = false,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTimestamp;

    @OneToMany
    private Set<ProductItem> productItems;

    public ShoppingList() {
        this.createdDate = LocalDateTime.now();
    }

    public void addProduct(ProductItem productItem) {
        productItems.add(productItem);
    }
}
