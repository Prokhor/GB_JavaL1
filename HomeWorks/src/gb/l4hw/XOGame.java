package gb.l4hw;

import java.util.Random;
import java.util.Scanner;

public class XOGame {

    private static Scanner in;
    private static Random rand;

    private static int playingFieldSize;   // размер игрового поля
    private static int score;   // до сокльки играем
    private final static char DOT_X = 'X';  // символ 1 игрока
    private final static char DOT_O = 'O';  // символ 2 игрока
    private final static char DOT_EMPTY = '\u2022'; // символ пустой клетки
    private static char[][] playingFieldMap;    // игровое поле
    private static char humanSide;  // за кого играет человек
    private static String winnerLine = "";   // линия победителя для проверки

    public static void main(String[] args) {
        in = new Scanner(System.in);
        rand = new Random();
        playGame();
    }

    /**
     * Запуск игры
     */
    private static void playGame() {
        System.out.println("Введите размер игрового поля (от 3 до 9):");
        playingFieldSize = in.nextInt();
        if (playingFieldSize == 3){
            score = 3;
        } else {
            System.out.printf("До скольки очков играем (от 3 до %d)?\n", playingFieldSize);
            score = in.nextInt();
        }
        System.out.printf("Игра до %d очков.\n", score);
        initPlayingField();
        drawPlayingField();
        getWinnerLine();

        System.out.println("За кого вы будете играть (X или O)?");
        humanSide = in.next().toUpperCase().charAt(0);
        System.out.println("Начнём игру!");

        while (true) {
            if (humanSide == 'X') {
                humanTurn(humanSide);
                if (hasWinner(humanSide)) {
                    System.out.println("Вы победили, поздравляем!!!");
                    break;
                }
                if (isFull(humanSide)){
                    System.out.println("Ничья!");
                    break;
                }
                aiTurn();
                if (hasWinner('O')) {
                    System.out.println("Компьютер победил...");
                    break;
                }
            } else {
                aiTurn();
                if (hasWinner('X')) {
                    System.out.println("Компьютер победил...");
                    break;
                }
                if (isFull('X')){
                    System.out.println("Ничья!");
                    break;
                }
                humanTurn(humanSide);
                if (hasWinner(humanSide)) {
                    System.out.println("Вы победили, поздравляем!!!");
                    break;
                }
            }
            if (isFull('O')){
                System.out.println("Ничья!");
                break;
            }
        }
    }

    /**
     * Генерируем победную строку для проверки победителя
     */
    private static void getWinnerLine(){
        for (int i = 0; i < score; i++) {
            winnerLine += "*";
        }
    }

    /**
     * Инициализация игрового поля
     */
    private static void initPlayingField() {
        playingFieldMap = new char[playingFieldSize][playingFieldSize];
        for (int i = 0; i < playingFieldSize; i++) {
            for (int j = 0; j < playingFieldSize; j++) {
                playingFieldMap[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void drawPlayingField() {
        System.out.print("  ");
        for (int i = 0; i < playingFieldSize; i++) {
            System.out.printf("%d ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < playingFieldSize; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < playingFieldSize; j++) {
                System.out.printf("%c ", playingFieldMap[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Проверка - доступна ли клетка и корректны ли введённые данные
     *
     * @param x координата X (номер столбца)
     * @param y координата Y (номер строки)
     */
    private static boolean isNotValid(int x, int y, boolean ai) {
        if (y <= 0 || y > playingFieldSize || x <= 0 || x > playingFieldSize) {
            if (!ai) {
                System.out.println("Введённые координаты вне поля!");
            }
            return true;
        }
        if (playingFieldMap[y - 1][x - 1] != DOT_EMPTY) {
            if (!ai) {
                System.out.println("Клетка занята!");
            }
            return true;
        }
        return false;
    }

    /**
     * Ход игрока
     */
    private static void humanTurn(char humanSide) {
        int x;
        int y;
        do {
            System.out.println("Ваш ход. Введи координаты (X Y):");
            x = in.nextInt();
            y = in.nextInt();
        } while (isNotValid(x, y, false));
        playingFieldMap[y - 1][x - 1] = humanSide;
        drawPlayingField();
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        char aiSide;
        if (humanSide == 'X') {
            aiSide = 'O';
        } else {
            aiSide = 'X';
        }
        int x;
        int y;
        boolean aiSet = false;
        for (int i = 0; i < playingFieldSize; i++) {
            if (!aiSet) {
                for (int j = 0; j < playingFieldSize; j++) {
                    if (playingFieldMap[i][j] == DOT_EMPTY) {
                        playingFieldMap[i][j] = aiSide;
                        if (hasWinner(aiSide)) {
                            playingFieldMap[i][j] = aiSide;
                            aiSet = true;
                            break;
                        } else {
                            playingFieldMap[i][j] = DOT_EMPTY;
                        }
                        playingFieldMap[i][j] = humanSide;
                        if (hasWinner(humanSide)) {
                            playingFieldMap[i][j] = aiSide;
                            aiSet = true;
                            break;
                        } else {
                            playingFieldMap[i][j] = DOT_EMPTY;
                        }
                    }
                }
            }
        }
        if (!aiSet) {
            do {
                x = rand.nextInt(playingFieldSize) + 1;
                y = rand.nextInt(playingFieldSize) + 1;
            } while (isNotValid(x, y, true));
            playingFieldMap[y - 1][x - 1] = aiSide;
        }
        drawPlayingField();
    }

    /**
     * Определяем есть ли победитель
     * @param c сторона победителя
     * @return true если есть победитель со стороной c
     */
    private static boolean hasWinner(char c) {
        for (int i = 0; i <= playingFieldSize - score; i++) {
            for (int j = 0; j <= playingFieldSize - score; j++) {
                if (checkCurrentMap(i, j, c)){
                    return true;
                };
            }
        }
        return false;
    }

    /**
     * Определяем есть ли в принципе победное поле
     * @param c потенциально победная сторона
     * @return true если есть незаполненная линия для стороны c
     */
    private static boolean potentialWinner(char c){
        for (int i = 0; i <= playingFieldSize - score; i++) {
            for (int j = 0; j <= playingFieldSize - score; j++) {
                if (checkCurrentMap(i, j, c, true)){
                    return true;
                };
            }
        }
        return false;
    }

    /**
     * Инициализация текущего поля из всего игрового для проверки
     * @param i сдвиг по строке
     * @param j сдвиг по столбцу
     * @return char[score][score]
     */
    private static char[][] initCurrentMap(int i, int j){
        char[][] currentMap = new char[score][score];
        for (int k = 0; k < score; k++) {
            System.arraycopy(playingFieldMap[k + i], j, currentMap[k], 0, score);
        }
        return currentMap;
    }

    /**
     * Проверка каждого возможного победного поля
     * @param i сдвиг поля по сторке
     * @param j сдвиг поля по столбцу
     * @param c сторона проверки
     * @return false если нет победной линии, true если есть
     */
    private static boolean checkCurrentMap(int i, int j, char c){
        char[][] currentMap = initCurrentMap(i, j);
        StringBuilder mdLine = new StringBuilder();
        StringBuilder sdLine = new StringBuilder();
        for (int k = 0; k < score; k++) {
            StringBuilder hLine = new StringBuilder();
            StringBuilder vLine = new StringBuilder();
            for (int l = 0; l < score; l++) {
                if (currentMap[k][l] == c){
                    hLine.append("*");
                }
                if (currentMap[l][k] == c){
                    vLine.append("*");
                }
                if (k == l && currentMap[k][l] == c){
                    mdLine.append("*");
                }
                if (k + l == score - 1 && currentMap[k][l] == c){
                    sdLine.append("*");
                }
            }
            if (winnerLine.equals(hLine.toString()) || winnerLine.equals(vLine.toString())){
                return true;
            };
        }
        return winnerLine.equals(mdLine.toString()) || winnerLine.equals(sdLine.toString());
    }

    /**
     * Проверка каждого потенциально победного поля
     * @param i сдвиг поля по сторке
     * @param j сдвиг поля по столбцу
     * @param c сторона проверки
     * @param potential флаг для перегрузки
     * @return false если нет потенциально победной линии, true если есть
     */
    private static boolean checkCurrentMap(int i, int j, char c, boolean potential){
        char[][] currentMap = initCurrentMap(i, j);
        StringBuilder mdLine = new StringBuilder();
        StringBuilder sdLine = new StringBuilder();
        for (int k = 0; k < score; k++) {
            StringBuilder hLine = new StringBuilder();
            StringBuilder vLine = new StringBuilder();
            for (int l = 0; l < score; l++) {
                if (currentMap[k][l] == c || currentMap[k][l] == DOT_EMPTY){
                    hLine.append("*");
                }
                if (currentMap[l][k] == c || currentMap[l][k] == DOT_EMPTY){
                    vLine.append("*");
                }
                if (k == l && currentMap[k][l] == c || k == l && currentMap[k][l] == DOT_EMPTY){
                    mdLine.append("*");
                }
                if (k + l == score - 1 && currentMap[k][l] == c || k + l == score - 1 && currentMap[k][l] == DOT_EMPTY){
                    sdLine.append("*");
                }
            }
            if (winnerLine.equals(hLine.toString()) || winnerLine.equals(vLine.toString())){
                return true;
            };
        }
        return winnerLine.equals(mdLine.toString()) || winnerLine.equals(sdLine.toString());
    }

    /**
     * Проверка есть ли смысл играть дальше
     * @return true если есть возможная победная линия
     */
    private static boolean isFull(char c){
        return !potentialWinner(c);
    }
}