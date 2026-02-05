import javax.swing.*;
import javax.swing.border.EmptyBorder; // สำหรับทำระยะห่างขอบ
import java.awt.*;

public class UI {
    public static void main(String[] args) {
        // สร้างหน้าต่างหลัก
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(400, 550); // ปรับขนาดให้โปร่งขึ้น
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ใช้ JPanel หลักพร้อมระยะห่างขอบ 20px รอบด้าน
        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(mainPanel);

        // ส่วนแสดงผล
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(0, 70)); // เพิ่มความสูงช่องแสดงผล
        mainPanel.add(textField, BorderLayout.NORTH);

        // ส่วนของปุ่ม
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 15, 15));

        //เรียงปุ่ม
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setFocusPainted(false); // ลบเส้นขอบเวลาโฟกัสปุ่ม
            
            // การแสดงผลตัวเลขเบื้องต้น 
            btn.addActionListener(e -> {
                String cmd = e.getActionCommand();
                if (cmd.equals("C")) {
                    textField.setText("");
                } else if (cmd.equals("=")) {
                    // รอเชื่อมต่อ Logic จากสมาชิกกลุ่มคนอื่น
                    System.out.println("Input received: " + textField.getText());
                } else {
                    if ("+-*/%".contains(cmd)) {
                        textField.setText(textField.getText() + " " + cmd + " ");
                    } else {
                        textField.setText(textField.getText() + cmd);
                    }
                }
            });
            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // สั่งให้หน้าต่างแสดงผล
        frame.setVisible(true);
    }
}