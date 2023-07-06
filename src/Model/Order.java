package Model;

import Entiti.Customer;
import Entiti.Product;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static int countIDOrder = 1;
    private int id;
    private Account account;
    private Customer customer;
    private List<Product> products;
    private float totalMoney;

    public Order(Account account, Customer customer, List<Product> products) {
        this.id = countIDOrder++;
        this.account = account;
        this.customer = customer;
        this.products = products;
        this.totalMoney = 0;
        for (Product product : products) {
            this.totalMoney += product.getToTalPrice();
        }
    }

    public static int getCountIDOrder() {
        return countIDOrder;
    }

    public static void setCountIDOrder(int countIDOrder) {
        Order.countIDOrder = countIDOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", account=" + account +
                ", customer=" + customer +
                ", products=" + products +
                ", totalMoney=" + totalMoney +
                '}';
    }
}