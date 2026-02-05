public class Calculator {
    public static void main(String[] args) {
        Logic logic = new Logic();
        UI ui = new UI(logic);
        ui.show();
    }
}
