package be.ehb.lennert.application.frameworks.demo.model.CRM;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column( name = "user_id")
    @JsonProperty
    private String id;

    private String frontName;
    private String lastName;
    private String address;

    @Column(nullable = false)
    @JsonProperty
    private String username;
    @JsonProperty
    @Column(nullable = false)
    private String email;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orders = new ArrayList<Order>();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String geteMail() {
        return email;
    }

    public void seteMail(String eMail) {
        this.email = eMail;
    }



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
