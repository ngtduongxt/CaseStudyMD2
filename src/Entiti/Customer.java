package Entiti;

public class Customer extends Person{
    private static int countIDCustomer = 1;
    private int id;

    public Customer(String name, String gender, String birthday, String phone, String email) {
        super(name, gender, birthday, phone, email);
        this.id = countIDCustomer++;
    }

    public static int getCountIDCustomer() {
        return countIDCustomer;
    }

    public static void setCountIDCustomer(int countIDCustomer) {
        Customer.countIDCustomer = countIDCustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}' + super.toString();
    }
}
