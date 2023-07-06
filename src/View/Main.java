package View;

import Entiti.Customer;
import Entiti.Product;
import Entiti.Staff;
import Function.Input;
import Manage.*;
import Model.*;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Input input = new Input();
    public static Login login = new Login();
    public static ManageAccount manageAccount = new ManageAccount();
    public static ManageCustomer manageCustomer = new ManageCustomer();
    public static ManageProduct manageProduct = new ManageProduct();
    public static ManageOrder manageOrder = new ManageOrder();

    public static void main(String[] args) {
        menuLogin();
    }

    public static void menuLogin() {
        while (true) {
            System.out.println("----------Menu Đăng Nhập----------");
            System.out.println("1. Đăng Nhập");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Account account = login.login();
                    if (account != null) {
                        if (account.getROLE().equals("ADMIN")) {
                            menuAdmin(account);
                        }
                        if (account.getROLE().equals("USER")) {
                            menuUser(account);
                        }
                    }
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public static void menuAdmin(Account account) {
        while (true) {
            System.out.println("----- Menu Admin -----");
            System.out.println("1. Quản Lý Tài Khoản");
            System.out.println("2. Quản Lý Sản Phẩm");
            System.out.println("3. Quản Lý Hóa Đơn");
            System.out.println("4. Quản Lý Khách Hàng");
            System.out.println("0. Đăng Xuất ");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    menuManageAccount();
                    break;
                case "2":
                    menuManageProduct();
                    break;
                case "3":
                    menuManageOrder(account);
                    break;
                case "4":
                    menuManageCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public static void menuUser(Account account) {
        while (true) {
            System.out.println("----- Menu Nhân Viên -----");
            System.out.println("1. Quản Lý Hóa Đơn");
            System.out.println("2. Quản Lý Khách Hàng");
            System.out.println("3. Điểm Danh");
            System.out.println("0. Đăng Xuất");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    menuManageOrder(account);
                    break;
                case "2":
                    menuManageCustomer();
                    break;
                case "3":
                    manageAccount.checkIn(account);
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public static void menuManageAccount() {
        while (true) {
            System.out.println("----- Menu Quản Lý Tài Khoản -----");
            System.out.println("1. Hiển Thị Tài Khoản");
            System.out.println("2. Thêm Tài Khoản");
            System.out.println("3. Sửa Tài Khoản");
            System.out.println("4. Xóa Tài Khoản");
            System.out.println("5. Tìm Tài Khoản");
            System.out.println("6. Tính Lương");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageAccount.displayAccount();
                    break;
                case "2":
                    manageAccount.addAccount();
                    break;
                case "3":
                    manageAccount.editAccount();
                    break;
                case "4":
                    manageAccount.deleteAccount();
                    break;
                case "5":
                    String username = input.inputString("Tài Khoản", Validate.USER_NAME_PATTERN);
                    Account account = manageAccount.findAccount(username);
                    if (account != null) {
                        if (account.getROLE().equals("ADMIN")) {
                            System.out.printf("%-5s%-10s%-10s%-13s%-15s%-26s%-10s%-10s%-10s%s",
                                    "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Tài Khoản", "Mật Khẩu", "Vai Trò", "\n");
                            System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s",
                                    account.getId(), account.getName(), account.getGender(), account.getBirthday(), account.getPhone(),
                                    account.getEmail(), account.getUsername(), account.getPassword(), account.getROLE(), "\n");
                        } else {
                            System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-10s%s",
                                    "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Tài Khoản", "Mật Khẩu", "Vai Trò", "\n");
                            System.out.printf("%-5s%-10s%-10s%-13s%-15s%-26s%-10s%-10s%-10s%-10s%s",
                                    account.getId(), account.getName(), account.getGender(), account.getBirthday(), account.getPhone(),
                                    account.getEmail(), ((Staff) account).getDaysOn(),
                                    account.getUsername(), account.getPassword(), account.getROLE(), "\n");
                        }
                    } else System.err.println("Không tìm thấy Tài Khoản!");
                    break;
                case "6":
                    manageAccount.salaryPayment();
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public static void menuManageProduct() {
        while (true) {
            System.out.println("-----Menu Quản Lý Sản Phẩm-----");
            System.out.println("1. Hiển Thị Sản Phẩm");
            System.out.println("2. Thêm Sản Phẩm");
            System.out.println("3. Sửa Sản Phẩm");
            System.out.println("4. Xóa Sản Phẩm");
            System.out.println("5. Tìm Sản Phẩm");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageProduct.displayProduct();
                    break;
                case "2":
                    manageProduct.addProduct();
                    break;
                case "3":
                    manageProduct.editProduct();
                    break;
                case "4":
                    manageProduct.deleteProduct();
                    break;
                case "5":
                    String nameProduct = input.inputString("Tên Sản Phẩm", Validate.NAME_PRODUCT_PATTERN);
                    Product product = manageProduct.findProductByName(nameProduct);
                    if (product != null) {
                        System.out.printf("%-5s%-15s%-15s%-15s%s",
                                "ID", "Tên", "Số Lượng", "Đơn Giá", "\n");
                        System.out.printf("%-5s%-20s%-10s%-10s%s",
                                product.getId(), product.getName(), product.getQuantity(), product.getPrice(), "\n");
                    } else System.err.println("Không Tìm Thấy Tên Sản Phẩm!");
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public static void menuManageOrder(Account account) {
        while (true) {
            System.out.println("----- Menu Quản Lý Hóa Đơn -----");
            System.out.println("1. Hiển Thị Sản Phẩm");
            System.out.println("2. Hiển Thị Hóa Đơn");
            System.out.println("3. Thêm Hóa Đơn");
            System.out.println("4. Sửa Hóa Đơn");
            System.out.println("5. Xóa Hóa Đơn");
            System.out.println("6. Tìm Hóa Đơn");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageProduct.displayProduct();
                    break;
                case "2":
                    manageOrder.displayOrder();
                    break;
                case "3":
                    manageOrder.addOrder(account);
                    break;
                case "4":
                    manageOrder.editOrder(account);
                    break;
                case "5":
                    manageOrder.deleteOrder(account);
                    break;
                case "6":
                    int id = input.inputInt("id", Validate.INT_PATTERN);
                    Order order = manageOrder.findOrderByID(id);
                    if (order != null) {
                        System.out.printf("%-5s%-25s%-25s%-15s%-15s%-15s%s",
                                "ID", "Vai Trò | Tên Tài Khoản", "Tên Khách Hàng", "Ngày Sinh", "SĐT", "Email", "\n");
                        System.out.printf("%-5s%-25s%-25s%-15s%-15s%-26s%s", order.getId(), order.getAccount().getROLE() + " | " + order.getAccount().getName(),
                                order.getCustomer().getName(), order.getCustomer().getBirthday(),
                                order.getCustomer().getPhone(), order.getCustomer().getEmail(), "\n");
                        System.out.println("--- Danh Sách Sản Phẩm Mua ---");
                        System.out.printf("%-5s%-15s%-15s%-15s%-15s%s",
                                "ID", "Tên", "Số Lượng", "Đơn Giá", "Tổng Tiền", "\n");
                        for (Product product : order.getProduct()) {
                            System.out.printf("%-5s%-15s%-15s%-15s%-15s%s",
                                    product.getId(), product.getName(), product.getQuantity(), product.getPrice(), product.getToTalPrice(), "\n");
                        }
                        System.out.println("===>> Tồng Tiền Đơn Hàng: " + order.getTotalMoney());
                    } else System.err.println("Không Tìm Thấy ID!");
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Nhập Không Hợp Lệ!");
            }
        }
    }

    public static void menuManageCustomer() {
        while (true) {
            System.out.println("-----Menu Quản Lý Khách Hàng-----");
            System.out.println("1. Hiển Thị Khách Hàng");
            System.out.println("2. Thêm Khách Hàng");
            System.out.println("3. Sửa Khách Hàng");
            System.out.println("4. Xóa Khách Hàng");
            System.out.println("5. Tìm Khách Hàng");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageCustomer.displayCustomer();
                    break;
                case "2":
                    manageCustomer.addCustomer();
                    break;
                case "3":
                    manageCustomer.editCustomer();
                    break;
                case "4":
                    manageCustomer.deleteCustomer();
                    break;
                case "5":
                    String name = input.inputString("Tên", Validate.NAME_PATTERN);
                    Customer customer = manageCustomer.findCustomerByName(name);
                    if (customer != null) {
                        System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%s",
                                "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "\n");
                        System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%s",
                                customer.getId(), customer.getName(), customer.getGender(), customer.getBirthday(),
                                customer.getPhone(), customer.getEmail(), "\n");
                    } else System.err.println("Không Tìm Thấy Tên Khách Hàng!");
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }
}