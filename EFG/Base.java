import javax.swing.*;
import java.awt.*;

public class Base extends JFrame {
    private int whiteGroupingSize;

    public Base(int whiteGroupingSize) {
        this.whiteGroupingSize = whiteGroupingSize;
        initialize();
    }

    private void initialize() {
        setTitle("Dark Blue Screen");
        setSize(540, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Fill the background with dark blue color
        g.setColor(new Color(0, 0, 100));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Calculate the position and size of the white grouping in the center
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int halfSize = whiteGroupingSize / 2;
        int x = centerX - halfSize;
        int y = centerY - halfSize;

        // Fill the white grouping of pixels in the center
        g.setColor(Color.WHITE);
        g.fillRect(x, y, whiteGroupingSize, whiteGroupingSize);
    }

    public static void main(String[] args) {
        int whiteGroupingSize = 35; // You can adjust this variable to change the size of the white grouping
        SwingUtilities.invokeLater(() -> new Base(whiteGroupingSize));
    }
}
