package gb.l6hw;

import java.util.Scanner;

public class Main {

    private static Scanner scan;
    private static Animal[] zoo;

    public static void main(String[] args) {

        scan = new Scanner(System.in);

        zoo = new Animal[10];

        zoo[0] = new Cat("Кузя", "Серый", 3, 40, 6);
        zoo[1] = new Cat("Барсик", "Белый", 5, 100);
        zoo[2] = new Cat("Рыжик", "Рыжий", 4);
        zoo[3] = new Cat("Стёпан", "Чёрный", 4, 70, 4);
        zoo[4] = new Cat("Мурзик", "Полосатый", 2);
        zoo[5] = new Animal("Животинка", "Синий", 10);
        zoo[6] = new Animal("Свининка", "Розовый", 1);
        zoo[7] = new Animal("Говядинка", "Коричневый", 1);
        zoo[8] = new Dog("Бобик", "Пятнистый", 5);
        zoo[9] = new Dog("Доберманчик", "Чёрный", 3, true);

        zoo[0].run(250);
        zoo[0].run(200);
        zoo[3].swim(10);
        zoo[6].run(-10);
        zoo[7].run(10);
        zoo[9].run(550);
        zoo[8].run(450);
        zoo[8].swim(10);
        zoo[2].swim(20);

        zoo[1].makeVoice();
        zoo[2].makeVoice();
        zoo[3].makeVoice();
        zoo[5].makeVoice();
        zoo[8].makeVoice();

        System.out.printf("\nИтого в нашем зоопарке %d животных. Среди них:\n%d котов;\n%d собак.\n\n", Animal.animalCount, Cat.catsCount, Dog.dogsCount);

        System.out.print("Хотите немножко поиграть (y/n)? ");
        String play = scan.next();
        while (!play.equals("y") && !play.equals("n")) {
            System.out.print("Неокрректный ввод.\nХотите немножко поиграть (y/n)? ");
            play = scan.next();
        }
        if (play.equals("y")) {
            playGame();
        }
    }

    private static void playGame() {
        System.out.println("Игра называется \"Погоняй кота!\"");
        Animal[] playingCats = initPlayCats(zoo);
        System.out.println("Какого кота погоняем?");
        int playCatNumber = scan.nextInt();
        System.out.printf("%s говорит: \"Спорим я пробегу сколько скажешь?\" \u263B\n%s\n", playingCats[playCatNumber - 1].getName(), ((Cat) playingCats[playCatNumber - 1]).getLives());
        int distance;
        do {
            System.out.print("Дистанция для кота: ");
            distance = scan.nextInt();
            ((Cat) playingCats[playCatNumber - 1]).run(distance, true);
        } while (((Cat) playingCats[playCatNumber - 1]).livesCount > 0);
    }

    private static Animal[] initPlayCats(Animal[] animals){
        Animal[] cats = new Cat[Cat.catsCount];
        for (int i = 0; i < Animal.animalCount; i++) {
            if (animals[i] instanceof Cat) {
                cats[i] = animals[i];
            }
        }
        for (int i = 0; i < Cat.catsCount; i++) {
            System.out.printf("%s - %s\n", i + 1, cats[i].getName());
        }
        return cats;
    }
}