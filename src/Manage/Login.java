package Manage;

import File.FilePATH;
import Function.IOFile;
import Function.Input;
import Model.Account;
import Model.Validate;

import java.util.List;
public class Login {
    IOFile<Account> ioFile = new IOFile<>();
    Input input = new Input();
    public Account login() {
        List<Account> accounts = ioFile.readDataFromFile(FilePATH.ACCOUNT_PATH);
        String username = input.inputString("Tài Khoản", Validate.USER_NAME_PATTERN);
        String password = input.inputString("Mật Khẩu", Validate.PASS_WORD_PATTERN);
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.err.println("Đăng Nhập Thành Công!");
                return account;
            }
        }
        System.err.println("Không Tìm Thấy Tài Khoản!");
        System.err.println("Vui Lòng Nhập Lại!");
        return null;
    }
}
