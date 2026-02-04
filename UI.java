import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI {

    private Logic logic;
    private JFrame frame;
    private JTextField textField;

    private double firstValue = 0;
    private String operator = "";

    public UI(Logic logic) {
        this.logic = logic;
        createUI();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void createUI() {
        frame = new JFrame("Simple Calculator");
        frame.setSize(400, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(mainPanel);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(0, 70));
        mainPanel.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 15, 15));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));

            btn.addActionListener(e -> handleButton(e.getActionCommand()));
            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void handleButton(String cmd) {
        if (cmd.equals("C")) {
            textField.setText("");
            logic.reset();
            operator = "";
        }
        else if ("+-*/%".contains(cmd)) {
            firstValue = Double.parseDouble(textField.getText());
            operator = cmd;
            textField.setText("");
        }
        else if (cmd.equals("=")) {
            double secondValue = Double.parseDouble(textField.getText());

            logic.setFirstValue(firstValue);
            logic.setOperator(operator);
            logic.setSecondValue(secondValue);

            double result = logic.calculate();
            textField.setText(String.valueOf(result));
        }
        else {
            textField.setText(textField.getText() + cmd);
        }
    }
}
