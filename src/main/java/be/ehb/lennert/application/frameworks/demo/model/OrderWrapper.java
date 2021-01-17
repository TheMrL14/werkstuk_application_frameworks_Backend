package be.ehb.lennert.application.frameworks.demo.model;

public class OrderWrapper {

    private long[] products;
    private String userId;

    public OrderWrapper(long[] products, String userId) {
        this.products = products;
        this.userId = userId;
    }

    public long[] getProducts() {
        return products;
    }

    public void setProducts(long[] products) {
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
