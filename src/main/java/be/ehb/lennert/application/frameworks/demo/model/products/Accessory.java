package be.ehb.lennert.application.frameworks.demo.model.products;

import be.ehb.lennert.application.frameworks.demo.model.Product;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accessories")
public class Accessory extends Product {
}
