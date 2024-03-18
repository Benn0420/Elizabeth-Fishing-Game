import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class GameplayScreen extends JFrame {
	
	private Timer timer;
    private JLabel gameOverLabel;
    private MainMenu mainMenu;
    private FishesScreen fishesScreen;
    // Initialize the indices with the default values from the CharacterStateManager
    CharacterStateManager characterStateManager = CharacterStateManager.getInstance();
    private int currentHatIndex = characterStateManager.getDefaultHatIndex();
    private int currentBoatIndex = characterStateManager.getDefaultBoatIndex();
    private int currentReelIndex = characterStateManager.getDefaultReelIndex();
    private boolean timerElapsed = true;
    private ArrayList<fishes> fishList;
    private JLabel ScoreCount;
    private int score = 0;
    private int weight = 0;
    
    public GameplayScreen(MainMenu mainMenu) {
    	this.mainMenu = mainMenu;

    	// Frame setup
        setTitle("Elizabeth's Fishing Trip - Gameplay");
        setSize(540, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Main panel with GridBagLayout for holding buttons and components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        getContentPane().add(mainPanel);
        
        // End Game button
        JButton EndButton = new JButton("EXIT");
        EndButton.setFont(new Font("Arial", Font.BOLD, 10)); // Make the font a bit smaller
        EndButton.setBackground(Color.WHITE);
        EndButton.setForeground(Color.RED);
        EndButton.setFocusPainted(false);
        GridBagConstraints EndButtonConstraints = new GridBagConstraints();
        EndButtonConstraints.gridx = 0;
        EndButtonConstraints.gridy = 0;
        EndButtonConstraints.fill = GridBagConstraints.NONE;
        EndButtonConstraints.anchor = GridBagConstraints.EAST;
        EndButtonConstraints.insets = new Insets(0, 0, 0, 0); 
        mainPanel.add(EndButton, EndButtonConstraints);

        // Title label panel with white background and rounded border
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(0, 10, 0, 10);

        // Title label
        JLabel titleLabel = new JLabel("Elizabeth's Fishing Trip");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleConstraints.gridy = GridBagConstraints.FIRST_LINE_START;
        titlePanel.add(titleLabel, titleConstraints);

        // Add the title panel to the main panel at the top
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 1;
        mainConstraints.weightx = 1.0;
        mainConstraints.anchor = GridBagConstraints.NORTH; // Anchor at the top
        mainPanel.add(titlePanel, mainConstraints);
        
        // Create the character panel and add it to the main panel
        JPanel characterPanel = createCharacterPanel();
        mainConstraints.gridy = 2;
        mainPanel.add(characterPanel, mainConstraints);

        // Create the wave stripe gif panel and add it to the character panel
        ImageIcon waveSIcon = new ImageIcon("wavesT.gif");
        JPanel waveSPanel = new JPanel();
        waveSPanel.setOpaque(false);
        waveSPanel.setLayout(new BorderLayout());
        waveSPanel.add(new JLabel(waveSIcon), BorderLayout.CENTER);
        mainConstraints.gridy = 2;
        mainPanel.add(waveSPanel, mainConstraints);
        
        // Creating the fish screen
        fishesScreen = new FishesScreen();
        fishList = fishesScreen.getFishList();
        fishesScreen.setOpaque(false);
        
        // Creating a JLayeredPane to hold the fishes and background images and score label
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new GridBagLayout());
        layeredPane.setOpaque(false);
        mainConstraints.gridy = 3;
        mainPanel.add(layeredPane, mainConstraints);
        
        // Score Label
        ScoreCount = new JLabel("CAUGHT: " + score);
        ScoreCount.setFont(new Font("Arial", Font.BOLD, 20)); // Make the font a bit smaller
        ScoreCount.setBackground(Color.WHITE);
        ScoreCount.setForeground(Color.RED);
        ScoreCount.setOpaque(true);
        GridBagConstraints ScoreCountConstraints = new GridBagConstraints();
        ScoreCountConstraints.gridx = 0;
        ScoreCountConstraints.gridy = 0;
        ScoreCountConstraints.fill = GridBagConstraints.NONE;
        ScoreCountConstraints.anchor = GridBagConstraints.SOUTH;
        layeredPane.add(ScoreCount, ScoreCountConstraints);
        
        // Add the fish screen to the layered pane
        GridBagConstraints fishConstraints = new GridBagConstraints();
        fishConstraints.gridx = 0;
        fishConstraints.gridy = 0;
        fishConstraints.weightx = 1.0;
        fishConstraints.weighty = 1.0;
        fishConstraints.fill = GridBagConstraints.BOTH;
        layeredPane.add(fishesScreen, fishConstraints);

        // Create the coral and add it to the main panel
        ImageIcon coralIcon = new ImageIcon("coral.png");
        // Resize the coralIcon to the desired height and width
        Image resizedImage = coralIcon.getImage().getScaledInstance(540, 335, Image.SCALE_SMOOTH);
        ImageIcon resizedCoralIcon = new ImageIcon(resizedImage);
        JLabel coralLabel = new JLabel(resizedCoralIcon);
        GridBagConstraints coralConstraints = new GridBagConstraints();
        coralConstraints.gridx = 0;
        coralConstraints.gridy = 0;
        coralConstraints.weightx = 1.0;
        coralConstraints.weighty = 1.0;
        coralConstraints.fill = GridBagConstraints.BOTH;
        layeredPane.add(coralLabel, coralConstraints);
        
        // Create the wave block gif panel and add it to the character panel
        ImageIcon waveBIcon = new ImageIcon("wavesB.gif");
        JPanel waveBPanel = new JPanel();
        waveBPanel.setOpaque(false);
        waveBPanel.setLayout(new BorderLayout());
        waveBPanel.add(new JLabel(waveBIcon), BorderLayout.CENTER);
        GridBagConstraints waveBConstraints = new GridBagConstraints();
        waveBConstraints.gridx = 0;
        waveBConstraints.gridy = 0;
        waveBConstraints.weightx = 1.0;
        waveBConstraints.weighty = 1.0;
        waveBConstraints.fill = GridBagConstraints.BOTH;
        layeredPane.add(waveBPanel, waveBConstraints);
        
		// Create the line canvas panel and add it to the main panel
        LineCanvas lineCanvas = new LineCanvas(281, 94, fishesScreen, fishesScreen.getFishList(), ScoreCount, score, weight);
        setGlassPane(lineCanvas);
        lineCanvas.setVisible(true);
        lineCanvas.setCanvasVisible(true);
        
        // Show rules pop-up when the screen loads
        showRulesPopup();
        
        // Create the Timer to shut down the game after 2 minutes
        timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	lineCanvas.setVisible(false);
            	timerElapsed = true;
                // Show the "GAME OVER" label
                showGameOverLabel();
                GridBagConstraints GameoverConstraints = new GridBagConstraints();
                GameoverConstraints.gridx = 0;
                GameoverConstraints.gridy = 0;
                GameoverConstraints.weightx = 1.0;
                GameoverConstraints.weighty = 1.0;
                GameoverConstraints.fill = GridBagConstraints.BOTH;
                layeredPane.add(gameOverLabel, GameoverConstraints);
                layeredPane.setLayer(gameOverLabel, 5);
                // Stop the Timer
                timer.stop();
                // Shut down the game after a delay
                Timer shutdownTimer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Exit the application
                    	GameoverScreen gameoverScreen = new GameoverScreen(lineCanvas.getScore(), lineCanvas.getWeight(), mainMenu);
                        gameoverScreen.setVisible(true);
                        dispose(); // Close the GameoverScreen
                    }
                });
                shutdownTimer.setRepeats(false);
                shutdownTimer.start();
            }
        });
        timer.setRepeats(false);
        timer.start();
        
        // Add mouseMoved listener to mainPanel
        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getY() > 20 && !timerElapsed) {
                    lineCanvas.setCanvasVisible(true); // Show the LineCanvas
                }
            }
        });
        
        // Add an ActionListener to the backButton to handle the click event
        EndButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	timer.stop();
            	// Exit the application
            	GameoverScreen gameoverScreen = new GameoverScreen(lineCanvas.getScore(), lineCanvas.getWeight(), mainMenu);
                gameoverScreen.setVisible(true);
                dispose(); // Close the GameoverScreen
            }
        });

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    private void showRulesPopup() {
        String rules = "<html><body style='width: 300px;'>" +
                       "<h1 style='text-align: center;color: #FFFFFF;font-weight: bold;'>Elizabeth's Fishing Rules!</h1>" +
                       "<div>" +
                       "<h2 'text-align: center;'>Instructions:</h2>" +
                       "<p>Once the Gamescreen loads you will only have 2 minutes.</p>" +
                       "<p>Click on the screen to cast your fishing line.</p>" +
                       "<p>Click on the fish to collect them as they swim across the screen.</p>" +
                        "<p>Avoid catching the seaweed.</p>" +
                       "<p>Try to collect as many fish as you can for a higher score.<p>" +
                       "<p>Good luck and have fun fishing!</p>" +
                       "</div>" +
                       "</body></html>";
        
        // Customizing the background color of the pop-up
        UIManager.put("OptionPane.background", Color.decode("#bcbefa"));
        UIManager.put("Panel.background", Color.decode("#bcbefa"));
        
        JOptionPane.showMessageDialog(GameplayScreen.this, rules, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showGameOverLabel() {
        // Create the "GAME OVER" label and add it to the main panel
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 80));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.NORTH);
    }

    private JPanel createCharacterPanel() {
    	// Create the character panel with GridBagLayout
        JPanel characterPanel = new JPanel(new GridBagLayout());
        characterPanel.setOpaque(false);
        GridBagConstraints characterConstraints = new GridBagConstraints();
        characterConstraints.gridx = 0;
        characterConstraints.gridy = 0;
        characterConstraints.weightx = 1.0;
        characterConstraints.weighty = 1.0;
        characterConstraints.fill = GridBagConstraints.BOTH;
        characterConstraints.anchor = GridBagConstraints.CENTER;

        // Load the images of the boat, character, hat, and reel
        ImageIcon womanIcon = new ImageIcon("elizabeth/woman.png");
        ImageIcon boatIcon = new ImageIcon("elizabeth/Boat_" + currentBoatIndex + ".png");
        ImageIcon hatIcon = new ImageIcon("elizabeth/Hat_" + currentHatIndex + ".png");
        ImageIcon reelIcon = new ImageIcon("elizabeth/Reel_" + currentReelIndex + ".png");

        // Stack the images on top of one another
        ImageIcon[] layerImages = {womanIcon, boatIcon, hatIcon, reelIcon};
        ImageIcon stackedIcon = stackImages(layerImages);

        // Create a JLabel to display the stacked character image
        JLabel characterLabel = new JLabel(stackedIcon);
        characterPanel.add(characterLabel, characterConstraints);

        return characterPanel;
    }

    // Helper method to stack images on top of one another
    private ImageIcon stackImages(ImageIcon[] layerImages) {
    	// Get the size of the character panel (540x100)
        int panelSizeW = 540;
        int panelSizeH = 100;

        // Create a new image with transparency to stack the images
        BufferedImage stackedImage = new BufferedImage(panelSizeW, panelSizeH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = stackedImage.createGraphics();

        // Draw the images on top of one another in the order provided
        int x = (panelSizeW - 100) / 2; // Center the character images horizontally
        int y = 0;
        for (ImageIcon imageIcon : layerImages) {
            Image image = imageIcon.getImage();
            g2d.drawImage(image, x, y, null);
        }

        g2d.dispose();

        // Return the stacked image as an ImageIcon
        return new ImageIcon(stackedImage);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new GameplayScreen(null));
    }
}

class LineCanvas extends JPanel implements MouseListener, MouseMotionListener {

	private FishesScreen fishesScreen;
	private int startX; // X-coordinate of the top of the reel
    private int startY; // Y-coordinate of the top of the reel
    private int endX;
    private int endY;
    private boolean tracking; // Indicates if tracking mouse movement
    private ArrayList<fishes> fishList; // Reference to the fish list from GameplayScreen
    private JLabel ScoreCount;
    private int score;
    private int weight;

    public LineCanvas(int startX, int startY, FishesScreen fishesScreen, ArrayList<fishes> fishList, JLabel ScoreCount, int score, int weight) {
    	
    	this.fishesScreen = fishesScreen;
    	this.startX = startX;
        this.startY = startY;
        this.endX = startX;
        this.endY = startY;
        this.tracking = false; // Set tracking to false initially
        this.fishList = fishList;
        this.ScoreCount = ScoreCount;
        this.score = score;
        this.weight = weight;
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this); // Add mouse motion listener
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
    }

    private void drawLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(startX, startY, endX, endY);
    }
    
    // Method to toggle the visibility of the LineCanvas
    public void setCanvasVisible(boolean visible) {
        setVisible(visible);
    }
    
    public boolean isTracking() {
        return tracking;
    }
    
    // Getter method for the fish list
    public List<fishes> getFishList() {
        return fishList;
    }

    // Getter method for the score
    public int getScore() {
        return score;
    }
    
    // Getter method for the weight
    public int getWeight() {
        return weight;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!tracking) {
            // If not already tracking, set the endpoint to the initial click position
            endX = e.getX();
            endY = e.getY();
            tracking = true; // Start tracking mouse movement
        } else {
            int mouseX = e.getX();
            int mouseY = e.getY() - 166;
            System.out.println("This was your click: " + mouseX + "," + mouseY);

            for (fishes fish : fishList) {
                int fishX = fish.getX();
                int fishY = fish.getY();
                Image fishImage = fish.getImage(); // Assuming fishes class has getImage() method that returns Image
                int fishWidth = fishImage.getWidth(null);
                int fishHeight = fishImage.getHeight(null);

                // Check if the click position is inside the bounds of the fish
                if (mouseX >= fishX && mouseX <= fishX + fishWidth &&
                    mouseY >= fishY && mouseY <= fishY + fishHeight) {
                	    // Fish is clicked, remove it from the screen
                	    fishList.remove(fish);
                	    fishesScreen.generateAdditionalFishes();
                	    repaint();
                	    System.out.println("Congrats, you caught one! ");
                	    score++;
                	    weight = weight + fishWidth;
                	    ScoreCount.setText("CAUGHT: " + score);
                	    break;
                	} else {
                	    //System.out.println("Oops, you missed" + fish.getImage());
                	}
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {
        // Update the endpoint based on the cursor's position while tracking
        if (tracking) {
            endX = e.getX();
            endY = e.getY();
            repaint();
        }
        
        if (e.getY() <= 20) {
            setCanvasVisible(false); // Hide the LineCanvas
        } else {
            setCanvasVisible(true);  // Show the LineCanvas
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
}
