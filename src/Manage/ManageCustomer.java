package Manage;

import File.FilePATH;
import Function.Create;
import Function.IOFile;
import Function.Input;
import Entiti.Customer;
import Model.Validate;

import java.util.List;

public class ManageCustomer {
    IOFile<Customer> ioFile = new IOFile<>();
    Create create = new Create();
    Input input = new Input();

    public ManageCustomer() {
    }

    public void displayCustomer() {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        System.out.println("------------------- Khách Hàng -------------------");
        System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%s",
                "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "\n");
        for (Customer customer : customers) {
            System.out.printf("%-5s%-10s%-15s%-15s%-15s%-15s%s",
                    customer.getId(), customer.getName(), customer.getGender(), customer.getBirthday(),
                    customer.getPhone(), customer.getEmail(), "\n");
        }
    }

    public void addCustomer() {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        Customer customer = create.customer();
        customers.add(customer);
        ioFile.writeDataToFile(customers, FilePATH.CUSTOMER_PATH);
    }

    public int findIndexByName(String name) {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().contains(name))  {
                return i;
            }
        }
        return -1;
    }

    public void editCustomer() {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        String name = input.inputString("Tên", Validate.NAME_PATTERN);
        int index = findIndexByName(name);
        if (index != -1) {
            Customer customer = create.customer();
            customer.setId(customers.get(index).getId());
            Customer.setCountIDCustomer(Customer.getCountIDCustomer() - 1);
            customers.set(index, customer);
            ioFile.writeDataToFile(customers, FilePATH.CUSTOMER_PATH);
        } else System.err.println("Không Tìm Thấy Tên!");
    }

    public void deleteCustomer() {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        String name = input.inputString("Tên", Validate.NAME_PATTERN);
        int index = findIndexByName(name);
        if (index != -1) {
            customers.remove(index);
            ioFile.writeDataToFile(customers, FilePATH.CUSTOMER_PATH);
        } else System.err.println("Không Tìm Thấy Tên!");
    }

    public Customer findCustomerByName(String name) {
        List<Customer> customers = ioFile.readDataFromFile(FilePATH.CUSTOMER_PATH);
        int index = findIndexByName(name);
        if (index != -1) return customers.get(index);
        return null;
    }
}
