package Manage;

import File.FilePATH;
import Function.Create;
import Function.IOFile;
import Function.Input;
import Model.Account;
import Entiti.Product;
import Model.Order;
import Model.Validate;

import java.util.List;
import java.util.Scanner;

public class ManageOrder {
    Scanner scanner = new Scanner(System.in);
    Create create = new Create();
    Input input = new Input();
    IOFile<Order> ioFileO = new IOFile<>();
    IOFile<Product> ioFileM = new IOFile<>();
    ManageProduct manageProduct = new ManageProduct();

    public ManageOrder() {
    }

    public void displayOrder() {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        System.out.println("------------------- Hóa Đơn -------------------");
        System.out.printf("%-5s%-30s%-20s%-15s%-15s%-15s%s",
                "ID", "ROLE | Tên Tài Khoản", "Tên Khách Hàng", "Ngày Sinh", "SĐT", "Email", "\n");

        for (Order order : orders) {
            System.out.printf("%-5s%-30s%-20s%-15s%-15s%-15s%s", order.getId(), order.getAccount().getROLE() + " | " + order.getAccount().getName(),
                    order.getCustomer().getName(), order.getCustomer().getBirthday(),
                    order.getCustomer().getPhone(), order.getCustomer().getEmail(), "\n");
            System.out.println("---------- Danh Sách Sản Phẩm Mua ----------");
            System.out.printf("%-5s%-20s%-15s%-15s%-15s%s",
                    "ID", "Tên", "Số Lượng", "Đơn Giá", "Tổng Đơn Hàng", "\n");
            for (Product product : order.getProduct()) {
                System.out.printf("%-5s%-20s%-15s%-15s%-15s%s",
                        product.getId(), product.getName(), product.getQuantity(), product.getPrice(), product.getToTalPrice(), "\n");
            }
            System.out.println("===>> Tổng Số Tiền Đơn Hàng: " + order.getTotalMoney());
            System.out.println("------------------------------------------");
        }
    }

    public void addOrder(Account account) {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        Order order = create.order(account);
        while (true) {
            List<Product> products = ioFileM.readDataFromFile(FilePATH.PRODUCT_PATH);
            System.out.println("----------- Thêm Hóa Đơn ------------");
            System.out.println("1. Thêm Sản Phẩm Vào Hóa Đơn");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String nameProduct = input.inputString("Tên Sản Phẩm", Validate.NAME_PRODUCT_PATTERN);
                    Product productBuy = manageProduct.findProductByName(nameProduct);
                    int index = manageProduct.findIndexByName(nameProduct);
                    if (productBuy != null) {
                        System.out.printf("%-5s%-15s%-15s%-15s%s",
                                "ID", "Tên", "Số Lượng", "Giá Tiền", "\n");
                        System.out.printf("%-5s%-15s%-15s%-15s%s",
                                productBuy.getId(), productBuy.getName(), productBuy.getQuantity(), productBuy.getPrice(), "\n");
                        int quantityThisProduct = productBuy.getQuantity();
                        int quantityNeedBuy = input.inputInt("Số Lượng Sản Phẩm", Validate.INT_PATTERN);
                        if (quantityNeedBuy <= quantityThisProduct) {
                            productBuy.setQuantity(quantityNeedBuy);
                            order.addProduct(productBuy);
                            float totalMoney = 0;
                            for (Product product : order.getProduct()) {
                                totalMoney += product.getToTalPrice();
                            }
                            order.setTotalMoney(totalMoney);
                            products.get(index).setQuantity(quantityThisProduct - quantityNeedBuy);
                            if (products.get(index).getQuantity() == 0) products.remove(index);
                            ioFileM.writeDataToFile(products, FilePATH.PRODUCT_PATH);
                        } else System.err.println("Số Lượng Quá Lớn!");
                    } else System.err.println("Không Tìm Thấy Tên Sản Phẩm!");
                    break;
                case "0":
                    orders.add(order);
                    ioFileO.writeDataToFile(orders, FilePATH.ORDER_PATH);
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public int findIndexByID(int id) {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) return i;
        }
        return -1;
    }

    public void editOrder(Account account) {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        int id = input.inputInt("id", Validate.INT_PATTERN);
        int index = findIndexByID(id);
        if (index != -1) {
            Order order = create.order(account);
            Order.setCountIDOrder(Order.getCountIDOrder() - 1);
            orders.set(index, order);
            ioFileO.writeDataToFile(orders, FilePATH.ORDER_PATH);
        } else System.err.println("Không Tìm Thấy ID!");
    }

    public void deleteOrder(Account account) {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        int id = input.inputInt("id", Validate.INT_PATTERN);
        int index = findIndexByID(id);
        if (index != -1) {
            orders.remove(index);
            ioFileO.writeDataToFile(orders, FilePATH.ORDER_PATH);
        } else System.err.println("Không Tìm Thấy ID!");
    }

    public Order findOrderByID(int id) {
        List<Order> orders = ioFileO.readDataFromFile(FilePATH.ORDER_PATH);
        int index = findIndexByID(id);
        if (index != -1) return orders.get(index);
        return null;
    }
}
