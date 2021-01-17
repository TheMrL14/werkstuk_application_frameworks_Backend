package be.ehb.lennert.application.frameworks.demo.model.CRM;


import be.ehb.lennert.application.frameworks.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

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
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;





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

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> product) {
        this.products = product;
    }

    public void addProduct(Product p){this.products.add(p);}
}
