package gb.l7hw;

public class Cat {
    private final String name;
    private int appetite;
    private boolean hungry;

    public Cat(String name, int appetite) {
        this(name, appetite, true);
    }

    public Cat(String name, int appetite, boolean hungry) {
        this.name = name;
        this.appetite = appetite;
        this.hungry = hungry;
    }

    public String getName() {
        return name;
    }

    public boolean isHungry() {
        return hungry;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void eat(Plate plate) {
        if (!hungry) {
            System.out.printf("%s: \"Я не голоден!\"\n", name);
            return;
        }
        if (plate.getFoodRemains() < appetite) {
            System.out.printf("%s: \"%s\"\n", name, plate.getFoodRemains() == 0 ? "Тарелка пуста!" : "В тарелке недостаточно еды!");
            return;
        }
        plate.decreaseFood(this);
    }
}