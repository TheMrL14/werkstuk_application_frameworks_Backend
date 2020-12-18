package be.ehb.lennert.application.frameworks.demo.model;

import be.ehb.lennert.application.frameworks.demo.model.CRM.Order;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductType category;
    private double price;
    private String description;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getCategory() {
        return category;
    }

    public void setCategory(ProductType category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    public void setOrder(Set<Order> order) {
        this.orders = order;
    }
}
