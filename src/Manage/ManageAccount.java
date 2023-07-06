package Manage;

import Entiti.Staff;
import File.FilePATH;
import Function.Create;
import Function.IOFile;
import Function.Input;
import Model.*;

import java.util.List;
import java.util.Scanner;

public class ManageAccount {
    Scanner scanner = new Scanner(System.in);
    Create create = new Create();
    Input input = new Input();
    IOFile<Account> ioFile = new IOFile<>();

    public ManageAccount() {
        //check account Admin
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        boolean isHasAdminAccount = false;
        for (Account account : accounts) {
            if (account.getUsername().equals("Admin")) {
                isHasAdminAccount = true;
                break;
            }
        }
        if (!isHasAdminAccount) {
            Manager manager = new Manager("Admin", "Male", "1/1/1111", "0987654321",
                    "Admin@gmail.com", "Admin", "Admin", "ADMIN");
            accounts.add(manager);
            ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
        }
    }

    public int findIndexByUserName(String username) {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().contains(username)) {
                return i;
            }
        }
        return -1;
    }

    public void displayAccount() {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        System.out.println("------------------- Tài Khoản Quản Lý -------------------");
        System.out.printf("%-5s%-10s%-15s%-15s%-15s%-20s%-17s%-17s%-10s%s",
                "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Tên Tài Khoản", "Mật Khẩu", "Vai Trò", "\n");
        for (Account account : accounts) {
            if (account.getROLE().equals("ADMIN")) {
                System.out.printf("%-5s%-10s%-15s%-15s%-15s%-20s%-17s%-17s%-10s%s",
                        account.getId(), account.getName(), account.getGender(), account.getBirthday(), account.getPhone(),
                        account.getEmail(), account.getUsername(), account.getPassword(), account.getROLE(), "\n");
            }
        }
        System.out.println("------------------- Tài Khoản Nhân Viên -------------------");
        System.out.printf("%-5s%-10s%-15s%-15s%-15s%-20s%-15s%-17s%-17s%-10s%s",
                "ID", "Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Ngày Đi Làm", "Tên Tài Khoản", "Mật Khẩu", "Vai Trò", "\n");

        for (Account account : accounts) {
            if (account.getROLE().equals("USER")) {
                System.out.printf("%-5s%-10s%-15s%-15s%-15s%-20s%-15s%-17s%-17s%-10s%s",
                        account.getId(), account.getName(), account.getGender(), account.getBirthday(), account.getPhone(),
                        account.getEmail(), ((Staff) account).getDaysOn(),
                        account.getUsername(), account.getPassword(), account.getROLE(), "\n");
            }
        }
    }

    public void addAccount() {
        while (true) {
            List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
            System.out.println("----- Menu Đăng Ký -----");
            System.out.println("1. Đăng Ký Quản Lý");
            System.out.println("2. Đăng Ký Nhân Viên");
            System.out.println("0. Thoát");
            System.out.print("Nhập Lựa Chọn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Manager manager = create.manager();
                    manager.setId(manager.getId() + 1);
                    accounts.add(manager);
                    ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
                    System.err.println("Đăng Ký Thành Công");
                    break;
                case "2":
                    Staff staff = create.staff();
                    accounts.add(staff);
                    ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
                    System.err.println("Đăng Ký Thành Công");
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa Chọn Không Hợp Lệ!");
            }
        }
    }

    public void editAccount() {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        String username = input.inputString("Tên Tài Khoản", Validate.USER_NAME_PATTERN);
        if (!username.equals("Admin")) {
            int index = findIndexByUserName(username);
            if (index != -1) {
                if (accounts.get(index) instanceof Manager) {
                    Manager manager = create.manager();
                    Account.setCountIDAccount(Account.getCountIDAccount() - 1);
                    accounts.set(index, manager);
                    ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
                }
                if (accounts.get(index) instanceof Staff) {
                    Staff staff = create.staff();
                    Account.setCountIDAccount(Account.getCountIDAccount() - 1);
                    accounts.set(index, staff);
                    ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
                }
            } else System.err.println("Không Tìm Thấy Tên Tài Khoản!");
        } else System.err.println("Không Thể Sửa Tài Khoản Admin!");
    }

    public void deleteAccount() {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        String username = input.inputString("Tên Tài Khoản", Validate.USER_NAME_PATTERN);
        if (!username.equals("Admin")) {
            int index = findIndexByUserName(username);
            if (index != -1) {
                accounts.remove(index);
                ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
            } else System.err.println("Không Tìm Thấy Tên Tài Khoản!");
        } else System.err.println("Không Thể Xóa Tài Khoản Admin!");
    }

    public Account findAccount(String username) {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        int index = findIndexByUserName(username);
        if (index != -1) {
            return accounts.get(index);
        }
        return null;
    }

    public void checkIn(Account account) {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(account.getUsername())) {
                index = i;
                break;
            }
        }
        if (account instanceof Staff) {
            Staff staff = (Staff) account;
            staff.setDaysOn(staff.getDaysOn() + 1);
            accounts.set(index, staff);
            ioFile.writeDataToFile(accounts, FilePATH.ACCOUNT_PATH);
        }
    }

    public void salaryPayment() {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        System.out.println("--- Lương Nhân Viên ---");
        System.out.printf("%-5s%-10s%-10s%-10s%-15s%s",
                "ID", "Tên", "Giới Tính", "Vai Trò", "Tiền Lương", "\n");
        for (Account staff : accounts) {
            if (staff instanceof Staff) {
                System.out.printf("%-5s%-10s%-10s%-10s%-15s%s",
                        staff.getId(), staff.getName(), staff.getGender(), staff.getROLE(),
                        ((Staff) staff).getToTalSalary(), "\n");
            }
        }
    }
}
