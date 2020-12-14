package gb.l8hw;

import javax.swing.*;
import java.awt.*;

public class TTTSettings extends JDialog {

    private static final int SETTINGS_WINDOW_WIDTH = 300;
    private static final int SETTINGS_WINDOW_HEIGHT = 550;

    private final int GAME_MIN_FIELD_SIZE = 3;
    private final int GAME_MAX_FIELD_SIZE = 10;

    private TTTGameWindow tttGameWindow;

    public TTTSettings(TTTGameWindow tttGameWindow){
        this.tttGameWindow = tttGameWindow;
        setTitle("Новая игра: настройки");
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\settings.png"));
        setSize(SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOW_HEIGHT);
        setResizable(false);
        setLayout(new GridLayout(8, 1));
        setModal(true);
        TicTacToe.setWindowPosition(this);
        initComponents();
    }

    private void initComponents(){
        JLabel lblGameMode = new JLabel("Выберите режим игры:");
        JRadioButton rbHumanVSAi = new JRadioButton("Игра против компьютера", true);
        JRadioButton rbHumanVSHuman = new JRadioButton("Игра против человека");
        rbHumanVSHuman.setEnabled(false);
        ButtonGroup bgGameMode = new ButtonGroup();
        bgGameMode.add(rbHumanVSAi);
        bgGameMode.add(rbHumanVSHuman);
        JLabel lblSize = new JLabel("Размер игрового поля:");
        JSlider slFieldSize = new JSlider(GAME_MIN_FIELD_SIZE, GAME_MAX_FIELD_SIZE, GAME_MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintTicks(true);
        slFieldSize.setPaintLabels(true);
        JLabel lblScore = new JLabel("До скольки очков игра:");
        JSlider slScore = new JSlider(GAME_MIN_FIELD_SIZE, GAME_MIN_FIELD_SIZE, GAME_MIN_FIELD_SIZE);
        slScore.setMajorTickSpacing(1);
        slScore.setPaintTicks(true);
        slScore.setPaintLabels(true);
        slFieldSize.addChangeListener(e->{
            slScore.setMaximum(slFieldSize.getValue());
        });
        JButton btnStart = new JButton("Начать игру");
        Icon startIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\img\\startgame.png"));
        btnStart.setIcon(startIcon);
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(e-> {
            TTTLogic.playingFieldSize = slFieldSize.getValue();
            TTTLogic.score = slScore.getValue();
            TTTLogic.humanSide = TTTLogic.DOT_X;
            TTTLogic.secondHumanSide = TTTLogic.DOT_O;
            TTTLogic.gameFinished = false;
            TTTLogic.initPlayingField();
            tttGameWindow.startNewGame(rbHumanVSAi.isSelected(), slFieldSize.getValue(), slScore.getValue());
            setVisible(false);
        });

        getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        add(lblGameMode);
        add(rbHumanVSAi);
        add(rbHumanVSHuman);
        add(lblSize);
        add(slFieldSize);
        add(lblScore);
        add(slScore);
        add(btnStart, BorderLayout.SOUTH);
    }

}
