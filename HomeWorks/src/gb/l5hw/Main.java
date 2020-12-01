package gb.l5hw;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван Иванович", "Ведущий программист-разработчик", "iiivanov@company.com", "+79991112233", 5000, 45);
        employees[1] = new Employee("Петров Пётр Петрович", "Тимлид", "pppetrov@company.com", "+79992223311", 6000, 39);
        employees[2] = new Employee("Сидоров Сидр Сидорович", "Джун", "sssidorov@company.com", "+79993331122", 1000, 20);
        employees[3] = new Employee("Семёнов Семён Семёнович", "Старший разработчик", "sssemenov@company.com", "+79995554433", 4000, 25);
        employees[4] = new Employee("Тётя Люба", "Завхоз", "tetyaluba@company.com", "112", 3000, 55);

        System.out.print("Вывести список сотрудников старше: ");
        int ageToCheck = scan.nextInt();

        for (Employee employee : employees) {
            if (employee.getEmployeeAge() > ageToCheck) {
                printEmployeeInfo(employee);
            }
        }

        System.out.print("Появился новый джун! Добавлен!\n");

        String fio = "Марк Цукерберг";
        String position = "Джун";
        String email = "mark@facebook.com";
        String phone = "9997766";
        int salary = 100;
        int age = 24;

        Employee newEmployee = new Employee().addNewEmployee(fio, position, email, phone, salary, age);
        printEmployeeInfo(newEmployee);
    }

    private static void printEmployeeInfo(Employee employee){
        System.out.println(employee.toString());
    }
}