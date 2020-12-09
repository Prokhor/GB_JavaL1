package gb.l1hw;

public class MainClass {
    public static void main(String[] args) {
        init();
        System.out.printf("%.3f\n", calc(6, 7, 17, 23));
        System.out.printf("%s\n", checkSum(-3, 13));
        checkSign(0);
        System.out.printf("%s\n", checkSign(0, true));
        greeting("Прохор");
        isLeap(2124);
    }

    /* Создать переменные всех пройденных типов данных и инициализировать их значения */
    private static void init() {
        byte b = 120;
        short sh = 30000;
        int k = 123456;
        long l = 123456789012345L;

        double d = 23.056;
        float f = 11.542f;

        char ch = '\u5463';

        boolean flag = true;
    }

    /* Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – аргументы этого метода, имеющие тип float */
    private static double calc(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    /* Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false */
    private static boolean checkSum(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    /* Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль, положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом */
    private static void checkSign(int a) {
        System.out.println(a >= 0 ? "Число положительное" : "Число отрицательное");
    }

    /* Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное, и вернуть false если положительное */
    /* P.S. от автора: flag исключительно для перегрузки и не важно его значение - взял на себя смелость немножко изменить условие */
    private static boolean checkSign(int a, boolean flag) {
        return !(a >= 0);
    }

    /* Написать метод, которому в качестве параметра передается строка, обозначающая имя. Метод должен вывести в консоль сообщение «Привет, указанное_имя!» */
    private static void greeting(String name) {
        System.out.printf("Привет, %s!\n", name);
    }

    /* Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный */
    private static void isLeap(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Год високосный.");
        } else {
            System.out.println("Год не високосный.");
        }
    }
}