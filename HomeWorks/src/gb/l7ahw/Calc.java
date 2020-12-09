package gb.l7ahw;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class Calc extends JFrame {
    private double a;
    private double b;
    private final Pattern OUTPUT_STRING = Pattern.compile("^[\\d]+[\\d\\+\\-\\*\\/\\.]+[\\d]+$");
    private String operator;

    public Calc(){
        this(300, 350);
    }

    public Calc(int width, int height) {
        setSize(width, height);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\calc.png"));
        setWindowPosition(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font font = new Font("TimesRoman", Font.PLAIN, 18);

        JTextField outputTextField = new JTextField();
        outputTextField.setEnabled(false);
        outputTextField.setFont(font);
        outputTextField.setDisabledTextColor(Color.black);
        add(outputTextField, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i < 10; i++) {
            centerPanel.add(setNewButton(String.valueOf(i), outputTextField));
        }
        centerPanel.add(setNewButton(".", outputTextField));
        centerPanel.add(setNewButton("0", outputTextField));
        centerPanel.add(setNewButton("=", outputTextField));
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        rightPanel.add(setNewButton("C", 55, outputTextField));
        rightPanel.add(setNewButton("+", 55, outputTextField));
        rightPanel.add(setNewButton("-", 55, outputTextField));
        rightPanel.add(setNewButton("*", 55, outputTextField));
        rightPanel.add(setNewButton("/", 55, outputTextField));

        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    public void setWindowPosition(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    private Button setNewButton(String text, JTextField jtf){
        Button btn = new Button(text);
        btn.setName(text);
        btn.addActionListener(e -> {
            if (btn.getName().equals("=")){
                if (checkOutputField(jtf.getText())){
                    a = Double.parseDouble(jtf.getText().split(operator)[0]);
                    b = Double.parseDouble(jtf.getText().split(operator)[1]);
                    switch (operator) {
                        case "\\+" -> jtf.setText(String.format("%s=%.3f", jtf.getText(), a + b));
                        case "\\-" -> jtf.setText(String.format("%s=%.3f", jtf.getText(), a - b));
                        case "\\*" -> jtf.setText(String.format("%s=%.3f", jtf.getText(), a * b));
                        case "\\/" -> jtf.setText(String.format("%s=%.3f", jtf.getText(), a / b));
                    }
                }
            } else {
                jtf.setText(jtf.getText() + btn.getName());
            }
        });
        return btn;
    }

    private Button setNewButton(String text, int width, JTextField jtf){
        Button btn = new Button(text);
        btn.setName(text);
        btn.setPreferredSize(new Dimension(width, getHeight()));
        btn.addActionListener(e -> {
            if (btn.getName().equals("C")){
                jtf.setText("");
                operator = "";
            } else {
                jtf.setText(jtf.getText() + btn.getName());
                operator = "\\" + btn.getName();
            }
        });
        return btn;
    }

    private boolean checkOutputField(String output){
        return output.matches(OUTPUT_STRING.pattern());
    }
}