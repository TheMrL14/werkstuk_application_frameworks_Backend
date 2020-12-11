package be.ehb.lennert.application.frameworks.demo.model.CRM;


import be.ehb.lennert.application.frameworks.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"user","product"})

public class Order {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    private Set<Product> products;


    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public Set<Product> getProduct() {
        return products;
    }

    public void setProduct(Set<Product> product) {
        this.products = product;
    }
}
