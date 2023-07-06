package Entiti;

import Model.Manager;

public class Staff extends Manager {
    private final int SALARY = 500000;
    private int daysOn;

    public Staff(String name, String gender, String birthday, String phone, String email,
                 String username, String password, String ROLE, int daysOn) {
        super(name, gender, birthday, phone, email, username, password, ROLE);
        this.daysOn = daysOn;
    }

    public int getSALARY() {
        return SALARY;
    }

    public int getDaysOn() {
        return daysOn;
    }

    public void setDaysOn(int daysOn) {
        this.daysOn = daysOn;
    }
    public int getToTalSalary() {
        return SALARY * daysOn;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "SALARY=" + SALARY +
                ", daysOn=" + daysOn +
                '}' + super.toString();
    }
}
