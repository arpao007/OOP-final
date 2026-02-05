public class Calculator {
    public static void main(String[] args) {
        // สั่งให้ Swing รันบน Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            Logic logic = new Logic();
            UI ui = new UI(logic);
            ui.show();
        });
    }
}