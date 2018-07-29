import javax.swing.*;

public class PlayWindow extends JFrame {

    public PlayWindow() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        PlayWindow window = new PlayWindow();
    }
}