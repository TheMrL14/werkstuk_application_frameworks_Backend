package be.ehb.lennert.application.frameworks.demo.model.products;

import be.ehb.lennert.application.frameworks.demo.model.MOMClassification;
import be.ehb.lennert.application.frameworks.demo.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "animal")
public class Animal extends Product {

    private String species;
    private long weight;
    private long height;
    private String colour;
    @Enumerated(EnumType.STRING)
    @Column(name = "Ministry_of_Magic_Classification")
    private MOMClassification classification;
    @Column(name = "native_to")
    private String nativeTo;


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public MOMClassification getClassification() {
        return classification;
    }

    public void setClassification(MOMClassification classification) {
        this.classification = classification;
    }

    public String getNativeTo() {
        return nativeTo;
    }

    public void setNativeTo(String nativeTo) {
        this.nativeTo = nativeTo;
    }
}
