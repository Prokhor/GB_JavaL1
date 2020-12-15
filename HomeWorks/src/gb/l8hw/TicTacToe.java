package gb.l8hw;

import java.awt.*;

public class TicTacToe {
    public static void main(String[] args) {
        new TTTGameWindow();
    }

    public static void setWindowPosition(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
