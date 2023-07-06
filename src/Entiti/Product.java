package Entiti;

import java.io.Serializable;

public class Product implements Serializable {
    private static int countIProduct = 1;
    private int id;
    private String name;
    private int quantity;
    private float price;

    public Product(String name, int quantity, float price) {
        this.id = countIProduct++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static int getCountIProduct() {
        return countIProduct;
    }

    public static void setCountIProduct(int countIProduct) {
        Product.countIProduct = countIProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getToTalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}