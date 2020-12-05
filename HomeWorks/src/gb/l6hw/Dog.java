package gb.l6hw;

public class Dog extends Animal{

    public static int dogsCount;

    private boolean fightingDog;

    private final int MAX_RUN_DISTANCE = 500;
    private final int MAX_SWIM_DISTANCE = 10;

// region Constructors
    public Dog(String name, String color, int age) {
        this(name, color, age, false);
    }

    public Dog(String name, String color, int age, boolean fightingDog) {
        super(name, color, age);
        this.fightingDog = fightingDog;
        dogsCount++;
    }
// endregion

    @Override
    public void makeVoice() {
        System.out.printf("%s говорит гав-гав!!!\n", getName());
    }

    @Override
    public void run(int distance) {
        if (distance > 0) {
            if (distance <= MAX_RUN_DISTANCE) {
                System.out.printf("%s пробежал %dм!\n", getName(), distance);
            } else {
                System.out.printf("%s пробежал %dм и упал!!!\n", getName(), MAX_RUN_DISTANCE);
            }
        } else {
            System.out.printf("%s run: Некорректная дистанция!\n", getName());
        }
    }

    @Override
    public void swim(int distance) {
        if (distance > 0) {
            if (distance <= MAX_SWIM_DISTANCE) {
                System.out.printf("%s пробежал %dм!\n", getName(), distance);
            } else {
                System.out.printf("%s подумал и решил не плыть...\n", getName());
            }
        } else {
            System.out.printf("%s swim: Некорректная дистанция!\n", getName());
        }
    }
}
