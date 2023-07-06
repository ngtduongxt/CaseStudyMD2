package Function;

import Model.Account;
import Entiti.Product;
import Model.Validate;

import java.util.List;
import java.util.Scanner;

public class Input {
    Validate validate = new Validate();
    Scanner scanner = new Scanner(System.in);

    public String inputString(String typeInputString, String regex) {
        String element;
        while (true) {
            System.out.print("Nhập " + typeInputString + " : ");
            String inputString = scanner.nextLine();
            if (validate.isValidateInputString(inputString, regex)) {
                element = inputString;
                return element;
            } else System.err.println("Nhập vào không hợp lệ!");
        }
    }

    public int inputInt(String typeInputString, String regex) {
        int element;
        while (true) {
            System.out.print("Nhập " + typeInputString + " : ");
            String inputString = scanner.nextLine();
            if (validate.isValidateInputString(inputString, regex)) {
                element = Integer.parseInt(inputString);
                return element;
            } else System.err.println("Nhập vào không hợp lệ!");
        }
    }

    public float inputFloat(String typeInputString, String regex) {
        float element;
        while (true) {
            System.out.print("Nhập " + typeInputString + " : ");
            String inputString = scanner.nextLine();
            if (validate.isValidateInputString(inputString, regex)) {
                element = Float.parseFloat(inputString);
                return element;
            } else System.err.println("Nhập vào không hợp lệ!");
        }
    }

    public String inputDuplicatedUserName(List<Account> accounts) {
        String element;
        while (true) {
            System.out.print("Nhập Tài Khoản : ");
            String inputString = scanner.nextLine();
            if (validate.isValidateInputString(inputString, Validate.USER_NAME_PATTERN)) {
                boolean isDuplicated = false;
                for (Account account : accounts) {
                    if (account.getUsername().equals(inputString)) {
                        isDuplicated = true;
                        break;
                    }
                } if (!isDuplicated) {
                    element = inputString;
                    return element;
                } else System.out.println("Tài khoản đã tồn tại!");
            } else System.out.println("Nhập vào không hợp lệ!");
        }
    }

    public String inputDuplicatedNameProduct(List<Product> products) {
        String element;
        while (true) {
            System.out.print("Nhập Tên Sản Phẩm : ");
            String inputString = scanner.nextLine();
            if (validate.isValidateInputString(inputString, Validate.NAME_PRODUCT_PATTERN)) {
                boolean isDuplicated = false;
                for (Product product : products) {
                    if (product.getName().equals(inputString)) {
                        isDuplicated = true;
                        break;
                    }
                }
                if (!isDuplicated) {
                    element = inputString;
                    return element;
                } else System.err.println("Đã Có Sản Phẩm Trong Kho!");
            } else System.err.println("Nhập vào không hợp lệ!");
        }
    }
}
