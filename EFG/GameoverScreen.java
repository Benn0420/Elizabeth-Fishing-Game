import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameoverScreen extends JFrame {

    private static final int SCREEN_WIDTH = 540;
    private static final int SCREEN_HEIGHT = 540;
    private MainMenu mainMenu;

    public GameoverScreen(int numFishCaught, int totalWeight, MainMenu mainMenu) {
    	
    	this.mainMenu = mainMenu;
    	
        // Frame setup
        setTitle("Farewell Fisher Friend - Game Over");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Main panel with GridBagLayout for holding components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        getContentPane().add(mainPanel);

        // Title label panel with white background and rounded border
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(10, 10, 20, 10);

        // Title label
        JLabel titleLabel = new JLabel("Farewell Fisher Friend");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        titleConstraints.gridy = GridBagConstraints.FIRST_LINE_START;
        titlePanel.add(titleLabel, titleConstraints);

        // Add the title panel to the main panel at the top
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.weightx = 1.0;
        mainConstraints.anchor = GridBagConstraints.NORTH; // Anchor at the top
        mainPanel.add(titlePanel, mainConstraints);

        // Label for number of fish caught
        JLabel numFishLabel = new JLabel("You caught " + numFishCaught + " fish");
        numFishLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainConstraints.gridy = 1;
        mainConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(numFishLabel, mainConstraints);

        // Label for total weight
        JLabel totalWeightLabel = new JLabel("They weighed " + totalWeight + " pounds");
        totalWeightLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainConstraints.gridy = 2;
        mainConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(totalWeightLabel, mainConstraints);

        // Label for the score
        int score = numFishCaught * totalWeight;
        JLabel scoreLabel = new JLabel("Your score is: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainConstraints.gridy = 3;
        mainConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(scoreLabel, mainConstraints);

        // Label for fisher level
        String fisherLevel = determineFisherLevel(score);
        JLabel levelLabel = new JLabel("You are a " + fisherLevel + " level fisher!!");
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainConstraints.gridy = 4;
        mainConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(levelLabel, mainConstraints);

        // Main Menu button
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(150, 50));
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 16));
        mainMenuButton.setBackground(Color.decode("#bcbefa"));
        //mainMenuButton.roundedBorder(15, new Color(192, 138, 208)));
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	// Code to switch back to the main menu screen goes here
                dispose(); // Close the current setting screen
                if (mainMenu != null) {
                    mainMenu.setVisible(true); // Show the main menu screen
                }
            }
        });
        mainConstraints.gridy = 5;
        mainConstraints.insets = new Insets(15, 10, 0, 10);
        mainPanel.add(mainMenuButton, mainConstraints);

        // Sun image
        ImageIcon sunIcon = new ImageIcon("sun.png");
        JLabel sunLabel = new JLabel(sunIcon);
        mainConstraints.gridy = 6;
        mainConstraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(sunLabel, mainConstraints);

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String determineFisherLevel(int score) {
        // Implement your logic here to determine the fisher level based on the score
        // For example, you can have specific score ranges for different levels.
        // You can return a string representing the fisher level.
        // You can create conditions like:
        if (score < 1000) {
        	return "beginner";
        } else if (score < 5000) {
        	return "intermediate";
        } else {
            return "expert";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameoverScreen(0, 0, null); // Example: passing numFishCaught=10 and totalWeight=50 as arguments.
        });
    }
}
