package gb.l8hw;

import java.util.Random;

public class TTTLogic {

    private static Random rand;

    public static int playingFieldSize;   // размер игрового поля
    public static int score;   // до сокльки играем
    public final static char DOT_X = 'X';  // символ 1 игрока
    public final static char DOT_O = 'O';  // символ 2 игрока
    public final static char DOT_EMPTY = '\u2022'; // символ пустой клетки
    public final static char WINNER_LINE_CHAR = '*';   // строка победной линии для проверки
    public static char[][] playingFieldMap;    // игровое поле
    public static char humanSide;  // за кого играет человек
    public static char secondHumanSide;  // второй игрок
    public static String winnerLine = "";   // линия победителя для проверки

    public static boolean gameFinished;
    public static boolean humanWin;
    public static boolean aiWin;

    /**
     * Запуск игры
     */
    public static void go() {
        gameFinished = true;
        //drawPlayingField();
        if (hasWinner(humanSide)) {
            //System.out.println("Вы победили, поздравляем!!!");
            humanWin = true;
            return;
        }
        if (isFull(humanSide)) {
            //System.out.println("Ничья!");
            humanWin = true;
            aiWin = true;
            return;
        }
        aiTurn();
        if (hasWinner(DOT_O)) {
            //System.out.println("Компьютер победил...");
            aiWin = true;
            return;
        }
        gameFinished = false;
    }

    /**
     * Генерируем победную строку для проверки победителя
     */
    private static void getWinnerLine(){
        winnerLine = "";
        for (int i = 0; i < score; i++) {
            winnerLine += WINNER_LINE_CHAR;
        }
    }

    /**
     * Инициализация игрового поля
     */
    public static void initPlayingField() {
        playingFieldMap = new char[playingFieldSize][playingFieldSize];
        for (int i = 0; i < playingFieldSize; i++) {
            for (int j = 0; j < playingFieldSize; j++) {
                playingFieldMap[i][j] = DOT_EMPTY;
            }
        }
        getWinnerLine();
        rand = new Random();
        humanWin = false;
        aiWin = false;
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
    public static void humanTurn(char humanSide, int x, int y) {
        if (!isNotValid(x, y, false)){
            playingFieldMap[y - 1][x - 1] = humanSide;
            go();
        }
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        char aiSide;
        if (humanSide == DOT_X) {
            aiSide = DOT_O;
        } else {
            aiSide = DOT_X;
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
        //drawPlayingField();
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
                    hLine.append(WINNER_LINE_CHAR);
                }
                if (currentMap[l][k] == c){
                    vLine.append(WINNER_LINE_CHAR);
                }
                if (k == l && currentMap[k][l] == c){
                    mdLine.append(WINNER_LINE_CHAR);
                }
                if (k + l == score - 1 && currentMap[k][l] == c){
                    sdLine.append(WINNER_LINE_CHAR);
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
                    hLine.append(WINNER_LINE_CHAR);
                }
                if (currentMap[l][k] == c || currentMap[l][k] == DOT_EMPTY){
                    vLine.append(WINNER_LINE_CHAR);
                }
                if (k == l && currentMap[k][l] == c || k == l && currentMap[k][l] == DOT_EMPTY){
                    mdLine.append(WINNER_LINE_CHAR);
                }
                if (k + l == score - 1 && currentMap[k][l] == c || k + l == score - 1 && currentMap[k][l] == DOT_EMPTY){
                    sdLine.append(WINNER_LINE_CHAR);
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