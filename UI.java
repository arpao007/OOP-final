import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI {

    private Logic logic;
    private JFrame frame;
    private JTextField textField;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewCalculation = false;

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

        // Display
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(0, 70));
        mainPanel.add(textField, BorderLayout.NORTH);

        // Buttons
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
            btn.setFocusPainted(false);
            btn.addActionListener(e -> handleButton(text));
            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void handleButton(String cmd) {
        if (cmd.equals("C")) {
            textField.setText("");
            logic.reset();
            firstValue = 0;
            operator = "";
            isNewCalculation = false;
        } 
        else if ("+-*/".contains(cmd)) {
            if (!operator.isEmpty() && !textField.getText().isEmpty()) {
                calculateCurrent();
            } else if (!textField.getText().isEmpty()) {
                firstValue = Double.parseDouble(textField.getText());
            }
            operator = cmd;
            textField.setText("");
            isNewCalculation = false;
        } 
        else if (cmd.equals("=")) {
            if (!operator.isEmpty() && !textField.getText().isEmpty()) {
                calculateCurrent();
                operator = "";
                isNewCalculation = true;
            }
        } 
        else {
            if (isNewCalculation) {
                textField.setText("");
                firstValue = 0;
                isNewCalculation = false;
            }
            textField.setText(textField.getText() + cmd);
        }
    }

    private void calculateCurrent() {
        try {
            double secondValue = Double.parseDouble(textField.getText());

            logic.setFirstValue(firstValue);
            logic.setOperator(operator);
            logic.setSecondValue(secondValue);

            double result = logic.calculate();
            textField.setText(String.valueOf(result));
            firstValue = result;

        } catch (Exception e) {
            textField.setText("Error");
        }
    }
}
