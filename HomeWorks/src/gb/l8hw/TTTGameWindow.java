package gb.l8hw;

import javax.swing.*;
import java.awt.*;

public class TTTGameWindow extends JFrame {

    private final int GAME_WINDOW_WIDTH = 356;
    private final int GAME_WINDOW_HEIGHT = 437;

    private TTTSettings tttSettings;
    private TTTBattleMap tttBattleMap;

    public TTTGameWindow(){
        setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Крестики-нолики");
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\tictactoe.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        TicTacToe.setWindowPosition(this);

        tttSettings = new TTTSettings(this);
        tttBattleMap = new TTTBattleMap(this);
        add(tttBattleMap, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton btnNewGame = new JButton("Новая игра");
        Icon startIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\img\\newgame.png"));
        btnNewGame.setIcon(startIcon);
        btnNewGame.setFocusPainted(false);
        btnNewGame.addActionListener(e->{
            tttSettings.setVisible(true);
        });
        JButton btnClose = new JButton("Выход");
        Icon closeIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\img\\closegame.png"));
        btnClose.setIcon(closeIcon);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e->{
            System.exit(0);
        });
        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnClose);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void startNewGame(boolean vsAI, int size, int score){
        tttBattleMap.startNewGame(vsAI, size, score);
    }
}
