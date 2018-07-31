import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
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

    public GameField() {
        setBackground(Color.BLACK);
        importImages();
        initGame();
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            // starting position
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 40;
        }
        timer = new Timer(250, this);
        timer.start();
        // create apple
        createApple();
    }

    private void createApple() {

    }

    public void importImages() {
        ImageIcon appleImage = new ImageIcon("apple.png");
        apple = appleImage.getImage();
        ImageIcon dotImage = new ImageIcon("dot.png");
        dot = dotImage.getImage();
    }
}
