package Model;

import Entiti.Person;

public class Account extends Person {
    private static int countIDAccount = 1;
    private int id;
    private String username;
    private String password;
    private String ROLE;

    public Account(String name, String gender, String birthday, String phone, String email, String username, String password, String ROLE) {
        super(name, gender, birthday, phone, email);
        this.id = countIDAccount++;
        this.username = username;
        this.password = password;
        this.ROLE = ROLE;
    }

    public static int getCountIDAccount() {
        return countIDAccount;
    }

    public static void setCountIDAccount(int countIDAccount) {
        Account.countIDAccount = countIDAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ROLE='" + ROLE + '\'' +
                '}' + super.toString();
    }
}
