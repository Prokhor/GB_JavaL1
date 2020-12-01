package gb.l5hw;

import java.util.regex.Pattern;

public class Employee {

    private final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    private String employeeFIO;
    private String employeePosition;
    private String employeeEMail;
    private String employeePhone;
    private int employeeSalary;
    private int employeeAge;

    public Employee() {
    }

    public Employee(String employeeFIO, String employeePosition, String employeeEMail, String employeePhone, int employeeSalary, int employeeAge) {
        this.employeeFIO = employeeFIO;
        this.employeePosition = employeePosition;
        this.employeeEMail = employeeEMail;
        this.employeePhone = employeePhone;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s\nДолжность: %s\ne-mail: %s\nТелефон: %s\nЗарплата: %d\nВозраст: %d\n", employeeFIO, employeePosition, employeeEMail, employeePhone, employeeSalary, employeeAge);
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public Employee addNewEmployee(String employeeFIO, String employeePosition, String employeeEMail, String employeePhone, int employeeSalary, int employeeAge) {
        if (employeeFIO.length() <= 150) {
            this.employeeFIO = employeeFIO;
        }
        if (employeePosition.length() <= 50) {
            this.employeePosition = employeePosition;
        }
        if (isEMailValid(employeeEMail)){
            this.employeeEMail = employeeEMail;
        }
        this.employeePhone = employeePhone;
        if (employeeSalary > 0){
            this.employeeSalary = employeeSalary;
        }
        if (employeeAge >= 18){
            this.employeeAge = employeeAge;
        }

        return this;
    }

    private boolean isEMailValid(String email){
        return email.matches(EMAIL_REGEX.pattern());
    }
}