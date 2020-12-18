package be.ehb.lennert.application.frameworks.demo.model;

import java.util.Arrays;

public enum ProductType {
    animals,
    food,
    accessories;

    //https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string
    public static String[] getTypes() {
        return Arrays.toString(ProductType.values()).replaceAll("^.|.$", "").split(", ");
    }
}
