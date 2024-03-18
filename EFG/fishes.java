import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class fishes {
    private int x; // X-coordinate of the fish's position
    private int y; // Y-coordinate of the fish's position
    private int size; // Size of the fish (15, 25, or 35 pixels)
    private int direction; // Direction of the fish's movement (1 for left to right, -1 for right to left)
    private Image image; // The fish's image

    private ArrayList<String> fishImageFilenames; // List of fish image filenames

    private static final int MOVEMENT_SPEED = 2;
    private static final int SCREEN_WIDTH = 540;

    public fishes(int x, int y, ArrayList<String> fishImageFilenames) {
        this.x = x;
        this.y = y;
        this.fishImageFilenames = fishImageFilenames;
        initializeFish();
    }

    private void initializeFish() {
        Random random = new Random();
        int randomIndex = random.nextInt(fishImageFilenames.size());
        String imageName = fishImageFilenames.get(randomIndex);
        image = new ImageIcon(imageName).getImage();

        // Determine the initial movement direction (1 or -1)
        direction = -1;
    }

    public void move() {
        x += direction * MOVEMENT_SPEED;

        // Check if the fish has entered the visible area
        if (direction == 1 && x >= SCREEN_WIDTH) {
            // If the fish moves out of the visible area on the right, reset its position to the left
            x = -size;
            initializeFish();
        } else if (direction == -1 && x <= -size) {
            // If the fish moves out of the visible area on the left, reset its position to the right
            x = SCREEN_WIDTH;
            initializeFish();
        }
    }

    // Getter method for fish image
    public Image getImage() {
        return image;
    }

    // Getter method for fish size
    public int getSize() {
        return size;
    }

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
