package be.ehb.lennert.application.frameworks.demo.model.products;

import be.ehb.lennert.application.frameworks.demo.model.Product;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "food")
public class Food extends Product {
    private long weight;
    private boolean isAlive;

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
