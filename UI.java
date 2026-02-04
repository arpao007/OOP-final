import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI {
    private Logic logic;
    private JFrame frame;
    private JTextField textField;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewCalculation = false; // เพิ่มตัวแปรนี้เพื่อเช็คว่าเพิ่งคำนวณเสร็จหรือไม่
    
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
        
        textField = new JTextField(); // Display
        textField.setFont(new Font("Arial", Font.BOLD, 28)); // ตั้งค่าหน้าจอ
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false); //กดได้แค่ปุ่ม ไม่สามารถพิมพ์จากแป้นพิมพ์ได้
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
        // กดปุ่ม C (Clear)
        if (cmd.equals("C")) {
            textField.setText("");
            logic.reset();
            firstValue = 0;
            operator = "";
            isNewCalculation = false;
        }
        // กดปุ่ม operator (+, -, *, /, %)
        else if ("+-*/%".contains(cmd)) {
            // ถ้ามี operator อยู่แล้ว และมีตัวเลขใน textField
            // แสดงว่าต้องคำนวณก่อน (เช่น 2 + 1 แล้วกด + อีกครั้ง)
            if (!operator.isEmpty() && !textField.getText().isEmpty()) {
                calculateCurrent(); // คำนวณก่อน
            }
            // ถ้า textField ไม่ว่าง ให้เอาค่ามาเป็น firstValue
            else if (!textField.getText().isEmpty()) {
                firstValue = Double.parseDouble(textField.getText());
            }
            // ถ้า textField ว่างและเพิ่งคำนวณเสร็จ ก็ใช้ firstValue เดิม
            
            operator = cmd;
            textField.setText("");
            isNewCalculation = false;
        }
        // กดปุ่ม =
        else if (cmd.equals("=")) {
            if (!operator.isEmpty() && !textField.getText().isEmpty()) {
                calculateCurrent();
                operator = ""; // ล้าง operator หลังคำนวณเสร็จ
                isNewCalculation = true; // บอกว่าเพิ่งคำนวณเสร็จ
            }
        }
        // กดปุ่มตัวเลข (0-9)
        else {
            // ถ้าเพิ่งคำนวณเสร็จและกดตัวเลขใหม่ → เริ่มการคำนวณใหม่
            if (isNewCalculation) {
                textField.setText("");
                firstValue = 0;
                isNewCalculation = false;
            }
            textField.setText(textField.getText() + cmd);
        }
    }
    
    /**
     * Method สำหรับคำนวณและแสดงผล
     * ใช้เมื่อกด operator ใหม่หรือกด =
     */
    private void calculateCurrent() {
        try {
            double secondValue = Double.parseDouble(textField.getText());
            
            // ส่งค่าไปให้ Logic
            logic.setFirstValue(firstValue);
            logic.setOperator(operator);
            logic.setSecondValue(secondValue);
            
            // คำนวณ
            double result = logic.calculate();
            
            // แสดงผลลัพธ์
            textField.setText(String.valueOf(result));
            
            // เก็บผลลัพธ์เป็น firstValue สำหรับการคำนวณต่อ
            firstValue = result;
            
        } catch (NumberFormatException e) {
            textField.setText("Error");
        } catch (ArithmeticException e) {
            textField.setText("Error: " + e.getMessage());
        }
    }
}
