package Model;

public class Manager extends Account {
    public Manager(String name, String gender, String birthday, String phone, String email,
                   String username, String password, String ROLE) {
        super(name, gender, birthday, phone, email, username, password, ROLE);
    }

    @Override
    public String toString() {
        return "Manager{}" + super.toString();
    }
}
