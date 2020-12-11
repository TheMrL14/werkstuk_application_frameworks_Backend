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

    public Set<Order> getOrder() {
        return orders;
    }

    public void setOrder(Set<Order> order) {
        this.orders = order;
    }
}
