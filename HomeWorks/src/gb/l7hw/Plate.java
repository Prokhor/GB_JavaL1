package gb.l7hw;

public class Plate {

    private int food;
    private final int volume;

    public Plate(int food) {
        this.food = food;
        volume = food;
    }

    public int getFoodRemains() {
        return food;
    }

    public void decreaseFood(Cat cat) {
        if (cat.getAppetite() > 0) {
            food -= cat.getAppetite();
            System.out.printf("%s: \"Спасибо, я поел, было вкусно!\" \u263B\n%s", cat.getName(), food > 0 ?
                    String.format("Еды осталось: %d\n", food) : "Тарелка пуста!\n");
//            cat.setHungry(false);
        } else {
            System.out.printf("%s - этот кот неправильный... Накажем его!\n", cat.getName());
        }
        cat.setHungry(false);   // вынесено сюда, чтобы цикл в Main завершился
    }

    public void fillFood() {
        if (food < volume) {
            food = volume;
            System.out.println("Хозяин пополнил тарелку с едой!");
        }
    }
}