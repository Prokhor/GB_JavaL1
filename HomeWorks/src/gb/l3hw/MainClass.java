package gb.l3hw;

import java.util.Random;
import java.util.Scanner;

public class MainClass {

    private static Scanner sc;
    private static Random rand;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        rand = new Random();

        // 1.
        do {
            //playGame();
        } while (false);

        // 2.
        guessTheWord();
    }

    /* Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
       При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
       После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет) */
    private static void playGame() {
        int hidden = rand.nextInt(10);
        int counter = 3;
        System.out.println("Загадано число от 0 до 9. Попробуй угадать его.");
        do {
            System.out.println("Введи число:");
            int answer = sc.nextInt();
            if (hidden > answer) {
                counter--;
                System.out.printf("Загаданное число больше! Осталось попыток: %d\n", counter);
            } else if (hidden < answer) {
                counter--;
                System.out.printf("Загаданное число меньше! Осталось попыток: %d\n", counter);
            } else {
                System.out.println("Ты угадал, поздравляем!\n");
                counter = 0;
            }
        } while (counter > 0);
        System.out.println("«Повторить игру еще раз? 1 – да / 0 – нет»");
        int rerun = sc.nextInt();
        while (rerun != 0 && rerun != 1) {
            System.out.print("Неокрректный ввод.\n«Повторить игру еще раз? 1 – да / 0 – нет»\n");
            rerun = sc.nextInt();
        }
        if (rerun == 1) {
            playGame();
        }
    }

    /* *Создать массив из слов
       При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
       сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано,
       компьютер показывает буквы которые стоят на своих местах.
       apple – загаданное
       apricot - ответ игрока
       ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
       Играем до тех пор, пока игрок не отгадает слово
       Используем только маленькие буквы */
    private static void guessTheWord() {
        String[] words = {
                "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String hidden = words[rand.nextInt(words.length)];
        StringBuilder workingStringBuilder = new StringBuilder();
        workingStringBuilder.append("###############");
        System.out.println("Я загадал слово, попробуй его угадать.\nВведи слово:");
        String answer;
        do {
            answer = sc.nextLine();
            int chrCnt = 0;
            if (answer.length() >= hidden.length()) {
                chrCnt = hidden.length();
            } else {
                chrCnt = answer.length();
            }
            for (int i = 0; i < chrCnt; i++) {
                if (answer.charAt(i) == hidden.charAt(i)) {
                    workingStringBuilder.setCharAt(i, answer.charAt(i));
                }
            }
            System.out.println(workingStringBuilder);
            if (hidden.equals(answer)) {
                System.out.println("Поздравляю, ты угадал!");
            } else {
                System.out.println("Нет, попробуй ещё раз:");
            }
        } while (!hidden.equals(answer));
    }
}