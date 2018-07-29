import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        add(new GameFrame());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame window = new MainFrame();
    }
}