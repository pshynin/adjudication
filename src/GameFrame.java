import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image apple;
    private Image dot;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameFrame() {
        setBackground(Color.BLACK);
        loadImager();
    }

    public void loadImager() {
        ImageIcon appleImage = new ImageIcon("apple.png");
        apple = appleImage.getImage();
        ImageIcon dotImage = new ImageIcon("dot.png");
        dot = dotImage.getImage();
    }
}
