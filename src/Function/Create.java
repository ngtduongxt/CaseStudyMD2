package Function;

import Entiti.Customer;
import Entiti.Person;
import Entiti.Product;
import Entiti.Staff;
import File.FilePATH;
import Manage.ManageCustomer;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Create {
    Input input = new Input();
    IOFile<Product> ioFileM = new IOFile<>();
    IOFile<Account> ioFileA = new IOFile<>();
    IOFile<Customer> ioFileC = new IOFile<>();
    Scanner scanner = new Scanner(System.in);

    public Product products() {
        List<Product> products = ioFileM.readDataFromFile(FilePATH.PRODUCT_PATH);
        String name = input.inputDuplicatedNameProduct(products);
        int quantity = input.inputInt("Số Lượng", Validate.INT_PATTERN);
        float price = input.inputFloat("Đơn Giá", Validate.PRICE_PATTERN);
        return new Product(name, quantity, price);
    }

    public Person person() {
        String name = input.inputString("Tên", Validate.NAME_PATTERN);
        String gender = input.inputString("Giới Tính", Validate.GENDER_PATTERN);
        String birthday = input.inputString("Ngày Sinh", Validate.BIRTHDAY_PATTERN);
        String phone = input.inputString("SĐT", Validate.PHONE_PATTERN);
        String email = input.inputString("email", Validate.EMAIL_PATTERN);
        return new Person(name, gender, birthday, phone, email);
    }

    public Account account() {
        List<Account> accounts = ioFileA.readDataFromFile(FilePATH.ACCOUNT_PATH);
        Person person = person();
        String username = input.inputDuplicatedUserName(accounts);
        String password = input.inputString("Mật Khẩu", Validate.PASS_WORD_PATTERN);
        String ROLE = input.inputString("Vai Trò", Validate.ROLE_PATTERN);
        return new Account(person.getName(), person.getGender(), person.getBirthday(), person.getPhone(), person.getEmail(),
                username, password, ROLE);
    }

    public Manager manager() {
        Account account = account();
        Account.setCountIDAccount(Account.getCountIDAccount() - 1);
        return new Manager(account.getName(), account.getGender(), account.getBirthday(), account.getPhone(), account.getEmail(),
                account.getUsername(), account.getPassword(), account.getROLE());
    }

    public Staff staff() {
        Manager manager = manager();
        int daysOn = input.inputInt("Số Ngày Làm", Validate.INT_PATTERN);
        Account.setCountIDAccount(Account.getCountIDAccount() - 1);
        return new Staff(manager.getName(), manager.getGender(), manager.getBirthday(), manager.getPhone(), manager.getEmail(),
                manager.getUsername(), manager.getPassword(), manager.getROLE(), daysOn);
    }

    public Customer customer() {
        Person person = person();
        return new Customer(person.getName(), person.getGender(), person.getBirthday(), person.getPhone(), person.getEmail());
    }

    public Order order(Account account) {
        while (true) {
            System.out.println("Menu Tạo Hóa Đơn : ");
            System.out.println("1. Khách Hàng Cũ");
            System.out.println("2. Khách Hàng Mới");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    ManageCustomer manageCustomer = new ManageCustomer();
                    String name = input.inputString("Tên", Validate.NAME_PATTERN);
                    Customer customer = manageCustomer.findCustomerByName(name);
                    if (customer != null) {
                        List<Product> products = new ArrayList<>();
                        return new Order(account, customer, products);
                    } else System.err.println("Không Tìm Thấy Khách Hàng Cũ!");
                case "2":
                    List<Customer> customers = ioFileC.readDataFromFile(FilePATH.CUSTOMER_PATH);
                    Customer newCustomer = customer();
                    customers.add(newCustomer);
                    ioFileC.writeDataToFile(customers, FilePATH.CUSTOMER_PATH);
                    List<Product> products = new ArrayList<>();
                    return new Order(account, newCustomer, products);
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }
}
