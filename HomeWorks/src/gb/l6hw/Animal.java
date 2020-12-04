package gb.l6hw;

public class Animal {

    public static int animalCount;

    private String name;
    private String color;
    private int age;

// region Constructors
    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
        animalCount++;
    }
// endregion

    public void run(int distance) {
        if (distance > 0) {
            System.out.printf("%s пробежал %dм!\n", name, distance);
        } else {
            System.out.printf("%s run: Некорректная дистанция!\n", getName());
        }
    }

    public void swim(int distance) {
        if (distance > 0) {
            System.out.printf("%s проплыл %dм...\n", name, distance);
        } else {
            System.out.printf("%s swim: Некорректная дистанция!\n", getName());
        }
    }

    public void makeVoice() {
        System.out.printf("%s подал голос!\n", getName());
    }

    public void getAnimalInfo() {
        System.out.printf("Привет! Я %s, меня зовут %s, я %s и мой возраст %d.\n", getCurrentClass(), name, color, age);
    }

    private String getCurrentClass() {
        return getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }
}