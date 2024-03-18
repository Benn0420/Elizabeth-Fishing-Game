import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FishTestScreen extends JFrame {

    private static final int SCREEN_WIDTH = 540;
    private static final int SCREEN_HEIGHT = 335;
    private static final int NUM_FISHES = 20;
    private static final int NUM_FISH_IMAGES = 5;

    private ArrayList<fishes> fishList; // Declare fishList as a member variable

    public FishTestScreen() {
        setTitle("Fish Test Screen");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the fishList
        fishList = new ArrayList<>();

        // Create lists of fish image filenames for each group
        ArrayList<String> lFishImageFilenames = new ArrayList<>();
        ArrayList<String> mFishImageFilenames = new ArrayList<>();
        ArrayList<String> sFishImageFilenames = new ArrayList<>();

        for (int i = 0; i < NUM_FISH_IMAGES; i++) {
            lFishImageFilenames.add("fish/LFish_" + i + ".png");
            mFishImageFilenames.add("fish/MFish_" + i + ".png");
            sFishImageFilenames.add("fish/SFish_" + i + ".png");
        }

        // Generate random fish instances and add them to the list
        for (int i = 0; i < NUM_FISHES; i++) {
            int randomGroup = new Random().nextInt(3); // Randomly choose one of the three groups

            ArrayList<String> fishImageFilenames;
            switch (randomGroup) {
                case 0:
                    fishImageFilenames = lFishImageFilenames;
                    break;
                case 1:
                    fishImageFilenames = mFishImageFilenames;
                    break;
                case 2:
                    fishImageFilenames = sFishImageFilenames;
                    break;
                default:
                    fishImageFilenames = lFishImageFilenames;
                    break;
            }

            int randomX = (int) (Math.random() * (SCREEN_WIDTH - 35));
            int randomY = (int) (Math.random() * (SCREEN_HEIGHT - 35));
            fishes fish = new fishes(randomX, randomY, fishImageFilenames);
            fishList.add(fish);
        }

        // Add the background image using a custom JPanel
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Start a timer to update the fish positions and repaint the screen
        Timer timer = new Timer(30, e -> {
            for (fishes fish : fishList) {
                fish.move();
            }
            repaint();
        });
        timer.start();

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw the fish images on the screen
        for (fishes fish : fishList) {
            g.drawImage(fish.getImage(), fish.getX(), fish.getY(), this);
        }
    }

    // Custom JPanel to handle the background image and coral image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private Image coralImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon("wavesB.gif").getImage();
            coralImage = new ImageIcon("coral.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            // Draw the coral image at the bottom of the screen
            g.drawImage(coralImage, 0, getHeight() - coralImage.getHeight(this), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FishTestScreen::new);
    }
}






