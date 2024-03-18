import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FishesScreen extends JPanel {

    private static final int SCREEN_WIDTH = 540;
    private static final int SCREEN_HEIGHT = 335;
    private static final int NUM_FISHES = 20;
    private static final int NUM_FISH_IMAGES = 5;
    private ArrayList<String> lFishImageFilenames;
    private ArrayList<String> mFishImageFilenames;
    private ArrayList<String> sFishImageFilenames;

    private ArrayList<fishes> fishList; // Declare fishList as a member variable

    public FishesScreen() {
    	
        setLayout(new BorderLayout());

        // Initialize the fishList
        fishList = new ArrayList<>();

        // Create lists of fish image filenames for each group
        lFishImageFilenames = new ArrayList<>();
        mFishImageFilenames = new ArrayList<>();
        sFishImageFilenames = new ArrayList<>();

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
            //System.out.println("Added fish at: " + randomX + ", " + randomY);
            fishList.add(fish);
        }

        // Start a timer to update the fish positions and repaint the screen
        Timer timer = new Timer(30, e -> {
            for (fishes fish : fishList) {
                fish.move();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // This paints the background images and coral
        // Draw the fish images on top of the background
        for (fishes fish : fishList) {
            g.drawImage(fish.getImage(), fish.getX(), fish.getY(), this);
        }
    }
    
    // Getter method for the fish list
    public ArrayList<fishes> getFishList() {
        return fishList;
    }
    
    public void generateAdditionalFishes() {
    	
        int desiredNumFishes = 20; // Set your desired number of fishes here
        int currentNumFishes = fishList.size();
        int numFishesToAdd = desiredNumFishes - currentNumFishes;

        if (numFishesToAdd > 0) {
            // ... The logic to generate and add new fishes, similar to what you have in the constructor ...
            for (int i = 0; i < numFishesToAdd; i++) {
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
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fish Test Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            frame.add(new FishesScreen());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

