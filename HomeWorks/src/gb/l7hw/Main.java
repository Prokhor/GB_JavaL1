package gb.l7hw;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = new Cat[6];

        cats[0] = new Cat("Кузя", 15);
        cats[1] = new Cat("Барсик", 35);
        cats[2] = new Cat("Феликс", 25);
        cats[3] = new Cat("Рыжик", 45);
        cats[4] = new Cat("Жирок", 40);
        cats[5] = new Cat("Баг", -20);

        Plate plate = new Plate(50);

        boolean hasHungryCat;

        do {
            hasHungryCat = false;
            for (Cat cat : cats) {
                if (cat.isHungry()) {
                    hasHungryCat = true;
                }
                cat.eat(plate);
            }
            plate.fillFood();
        } while (hasHungryCat);
    }
}
