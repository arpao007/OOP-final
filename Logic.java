public class Logic {
    // ตัวแปรเก็บค่าตัวเลขตัวแรก (ก่อนกด operator)
    private double firstValue;
    // ตัวแปรเก็บค่าตัวเลขตัวที่สอง (หลังเลือก operator)
    private double secondValue;
    // ตัวแปรเก็บตัวดำเนินการทางคณิตศาสตร์ เช่น + - * / %
    private String operator;
    
    // constructor สำหรับสร้างอ็อบเจกต์ Logic
    public Logic() {
        reset();
    }
    
    // กำหนดค่าตัวเลขตัวแรก
    // จะถูกเรียกเมื่อผู้ใช้กรอกตัวเลขชุดแรกและกด operator
    public void setFirstValue(double value) {
        this.firstValue = value;
    }
    
    // กำหนดตัวดำเนินการ
    // รับค่าเครื่องหมาย + - * / %
    public void setOperator(String op) {
        this.operator = op;
    }
    
    // กำหนดค่าตัวเลขตัวที่สอง
    // จะถูกเรียกเมื่อผู้ใช้กรอกตัวเลขชุดที่สอง
    public void setSecondValue(double value) {
        this.secondValue = value;
    }
    
    // คำนวณผลลัพธ์ตาม operator ที่เลือก
    // จะถูกเรียกเมื่อผู้ใช้กดปุ่ม =
    public double calculate() {
        if (operator.equals("+")) {
            return firstValue + secondValue;
        }
        if (operator.equals("-")) {
            return firstValue - secondValue;
        }
        if (operator.equals("*")) {
            return firstValue * secondValue;
        }
        if (operator.equals("/")) {
            if (secondValue == 0) {
                throw new ArithmeticException("Divide by zero");
            }
            return firstValue / secondValue;
        }
        if (operator.equals("%")) {
            return firstValue % secondValue;
        }
        return 0;
    }
    
    // รีเซ็ตค่าทั้งหมด
    // ใช้เมื่อกดปุ่ม Clear หรือเริ่มการคำนวณใหม่
    public void reset() {
        firstValue = 0;
        secondValue = 0;
        operator = "";
    }
}