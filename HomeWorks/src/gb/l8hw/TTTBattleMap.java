package gb.l8hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class TTTBattleMap extends JPanel {

    private TTTGameWindow tttGameWindow;

    private boolean vsAI;
    private boolean sideX;
    private int size;
    private int score;
    private boolean isInit;
    int cellDimension;

    public TTTBattleMap(TTTGameWindow tttGameWindow) {
        this.tttGameWindow = tttGameWindow;
        setBackground(new Color(182, 241, 184));
        isInit = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellDimension + 1;
                int cellY = e.getY() / cellDimension + 1;
                if (!TTTLogic.gameFinished) {
                    TTTLogic.humanTurn(TTTLogic.humanSide, cellX, cellY);
                }
                repaint();
                winnerInfo();
            }
        });
    }

    public void startNewGame(boolean vsAI, int size, int score) {
        this.vsAI = vsAI;
        this.size = size;
        this.score = score;
        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            return;
        }
        cellDimension = getWidth() / size;
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(new Color(12, 54, 13));
        for (int i = 0; i <= size; i++) {
            int x = i * cellDimension;
            g.drawLine(x, 0, x, size * cellDimension);
        }
        for (int i = 0; i <= size; i++) {
            int y = i * cellDimension;
            g.drawLine(0, y, size * cellDimension, y);
        }

        for (int i = 0; i < TTTLogic.playingFieldSize; i++) {
            for (int j = 0; j < TTTLogic.playingFieldSize; j++) {
                if (TTTLogic.playingFieldMap[i][j] == TTTLogic.DOT_X) {
                    drawX(g, j, i);
                }
                if (TTTLogic.playingFieldMap[i][j] == TTTLogic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y) {
        x += 1;
        y += 1;
        ((Graphics2D) g).setStroke(new BasicStroke(1.8f));
        g.setColor(Color.BLUE);
        g.drawLine(x * cellDimension - cellDimension / 2 - cellDimension / 4, y * cellDimension - cellDimension / 2 - cellDimension / 4,
                x * cellDimension - cellDimension / 2 + cellDimension / 4, y * cellDimension - cellDimension / 2 + cellDimension / 4);
        g.drawLine(x * cellDimension - cellDimension / 2 + cellDimension / 4, y * cellDimension - cellDimension / 2 - cellDimension / 4,
                x * cellDimension - cellDimension / 2 - cellDimension / 4, y * cellDimension - cellDimension / 2 + cellDimension / 4);
    }

    private void drawO(Graphics g, int x, int y) {
        x += 1;
        y += 1;
        ((Graphics2D) g).setStroke(new BasicStroke(1.8f));
        g.setColor(Color.RED);
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(x * cellDimension - cellDimension / 2, y * cellDimension - cellDimension / 2,
                x * cellDimension - cellDimension / 2 + cellDimension / 4, y * cellDimension - cellDimension / 2 + cellDimension / 4);
        ((Graphics2D) g).draw(circle);
    }

    private void winnerInfo(){
        if (TTTLogic.aiWin && !TTTLogic.humanWin) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Компьютер победил...", "Система", JOptionPane.INFORMATION_MESSAGE);
        }
        if (TTTLogic.humanWin && !TTTLogic.aiWin) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Вы победили!\nПоздравляем!", "Система", JOptionPane.INFORMATION_MESSAGE);
        }
        if (TTTLogic.humanWin && TTTLogic.aiWin) {
            JPanel myRootPane = new JPanel();
            JOptionPane.showMessageDialog(myRootPane, "Ничья!", "Система", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}