package io.shopr.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdDate;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updateTimestamp;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Cart() {
        this.createdDate = LocalDateTime.now();
        this.updateTimestamp = new Date();
    }

    @PrePersist
    public void onPersist(){
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateTimestamp = new Date();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public long getUpdateTimestamp() {
        return updateTimestamp.toInstant().toEpochMilli();
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
