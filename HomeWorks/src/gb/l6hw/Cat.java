package gb.l6hw;

public class Cat extends Animal {

    public static int catsCount;

    private final int MAX_RUN_DISTANCE = 200;
    private final int MAX_SWIM_DISTANCE = 0;

    double makingChaosAtHome;
    int livesCount;

// region Constructors

    public Cat(String name, String color, int age) {
        this(name, color, age, 50, 9);
    }

    public Cat(String name, String color, int age, double makingChaosAtHome) {
        this(name, color, age, makingChaosAtHome, 9);
    }

    public Cat(String name, String color, int age, double makingChaosAtHome, int livesCount) {
        super(name, color, age);
        this.makingChaosAtHome = makingChaosAtHome;
        this.livesCount = livesCount;
        catsCount++;
    }

// endregion

    @Override
    public void run(int distance) {
        if (distance > 0) {
            if (distance <= MAX_RUN_DISTANCE) {
                System.out.printf("%s пробежал %dм! \u263B\n", getName(), distance);
            } else {
                System.out.printf("%s столько не пробежит... \u2639\n", getName());
            }
        } else {
            System.out.printf("%s run: Некорректная дистанция!\n", getName());
        }
    }

    public void run(int distance, boolean game){
        if (game) {
            if (distance > 0) {
                if (distance <= MAX_RUN_DISTANCE) {
                    System.out.printf("%s пробежал %dм!\n%s: \"Ну я же говорил! \u263B Давай ещё побегаем!\"\n%s\n", getName(), distance, getName(), getLives());
                } else {
                    livesCount--;
                    System.out.print(livesCount > 0 ?
                            String.format("%s: \"Да ну тебя! Так не честно... \u2639\"\n%s\n", getName(), getLives()) :
                            String.format("%s: \"Загонял ты меня... Пойду кушоц!\"\n", getName()));
                }
            } else {
                System.out.printf("%s run: Некорректная дистанция!\n", getName());
            }
        }
    }

    @Override
    public void swim(int distance) {
        if (distance > 0) {
            if (MAX_SWIM_DISTANCE == 0) {
                System.out.printf("%s не умеет плавать... \u2639\n", getName());
            } else if (distance > MAX_SWIM_DISTANCE) {
                System.out.printf("%s столько не проплывёт... \u2639\n", getName());
            } else if (distance <= MAX_SWIM_DISTANCE) {
                System.out.printf("%s проплыл %dм! \u263B\n", getName(), distance);
            }
        } else {
            System.out.printf("%s swim: Некорректная дистанция!\n", getName());
        }
    }

    @Override
    public void makeVoice() {
        System.out.printf("Принёс мне кушоц? Мяу! %s", makingChaosAtHome > 50 ?
                String.format("%s перевернул весь дом от радости и разбил горшок с цветком!\n", getName()) :
                String.format("%s потёрся об ногу \u263B\n", getName()));
    }

    public String getLives() {
        return String.format("%s health: %s\n", getName(), getLivesString());
    }

    private String getLivesString() {
        return "\u2764".repeat(Math.max(0, livesCount));
    }
}