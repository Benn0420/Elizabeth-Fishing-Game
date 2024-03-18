import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class CharacterSettingScreen extends JFrame {

    private JPanel characterPanel;
    private JLabel characterLabel;
    private JButton hatButton;
    private JButton boatButton;
    private JButton reelButton;
    private JButton lureButton;
    private JLabel backButton;
    private JButton saveButton;
    private HashMap<String, ImageIcon[]> characterImages;
    // Initialize the indices with the default values from the CharacterStateManager
    CharacterStateManager characterStateManager = CharacterStateManager.getInstance();
    private int currentHatIndex = characterStateManager.getDefaultHatIndex();
    private int currentBoatIndex = characterStateManager.getDefaultBoatIndex();
    private int currentReelIndex = characterStateManager.getDefaultReelIndex();
    private MainMenu mainMenu;

    public CharacterSettingScreen(MainMenu mainMenu) {
    	this.mainMenu = mainMenu;

        // Frame setup
        setTitle("Elizabeth's Fishing Trip - Character Settings");
        setSize(540, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Fancy medium thickness border
        int padding = 10;
        LineBorder roundedBorder = new LineBorder(Color.decode("#f1eb9c"), padding, true);

        // Main panel with GridBagLayout for holding buttons and components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        getContentPane().add(mainPanel);

        // Title label panel with white background and rounded border
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(roundedBorder);
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(10, 10, 10, 10);

        // Title label
        JLabel titleLabel = new JLabel("Character Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Decrease the font size to 28
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, titleConstraints);

        // Add the title panel to the main panel at the top
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 1;
        mainConstraints.gridy = 0;
        mainConstraints.weightx = 1.0;
        mainConstraints.anchor = GridBagConstraints.NORTH; // Anchor at the top
        mainConstraints.insets = new Insets(10, 10, 30, 10);
        mainPanel.add(titlePanel, mainConstraints);

        // Character image panel with GridBagLayout
        characterPanel = new JPanel(new GridBagLayout());
        int characterPanelSize = 100; // 100px for the character panel (100px image size, no padding)
        characterPanel.setPreferredSize(new Dimension(characterPanelSize, characterPanelSize));
        characterPanel.setBackground(Color.WHITE);
        characterPanel.setBorder(new LineBorder(Color.WHITE, 1));
        GridBagConstraints characterConstraints = new GridBagConstraints();
        characterConstraints.gridx = 1;
        characterConstraints.gridy = 1;
        characterConstraints.weightx = 0.0; // Set to 1.0 to fill the available horizontal space (if any)
        characterConstraints.weighty = 0.0; // Set to 0.0 to not fill the vertical space
        characterConstraints.fill = GridBagConstraints.BOTH; // Fill the available space
        characterConstraints.anchor = GridBagConstraints.CENTER; // Anchor at the center

        // Create the character images array
        characterImages = new HashMap<>();
        // Load the character images for the boat, woman, hat, reel, and lure
        ImageIcon[] boatImages = new ImageIcon[10];
        ImageIcon womanImage = new ImageIcon("elizabeth/woman.png");
        ImageIcon[] hatImages = new ImageIcon[10];
        ImageIcon[] reelImages = new ImageIcon[10];
        ImageIcon[] lureImages = new ImageIcon[10];
        // Load images for each category (boat, woman, hat, reel, lure) and add them to their respective arrays
        // Note: Make sure the images are in the "elizabeth" folder in the workplace directory of your project.
        for (int i = 0; i < 10; i++) {
            boatImages[i] = new ImageIcon("elizabeth/Boat_" + i + ".png");
            hatImages[i] = new ImageIcon("elizabeth/Hat_" + i + ".png");
            reelImages[i] = new ImageIcon("elizabeth/Reel_" + i + ".png");
            lureImages[i] = new ImageIcon("elizabeth/lure_" + i + ".png");
        }
        characterImages.put("boat", boatImages);
        characterImages.put("woman", new ImageIcon[]{womanImage});
        characterImages.put("hat", hatImages);
        characterImages.put("reel", reelImages);
        characterImages.put("lure", lureImages);

        // Create the character label to display the current character image
        characterLabel = new JLabel();
        updateCharacterImage();

        // Set GridBagConstraints for the character label
        GridBagConstraints characterLabelConstraints = new GridBagConstraints();
        characterLabelConstraints.gridx = 1;
        characterLabelConstraints.gridy = 0;
        characterLabelConstraints.weightx = 0.0;
        characterLabelConstraints.weighty = 0.0;
        characterLabelConstraints.fill = GridBagConstraints.BOTH;
        characterPanel.add(characterLabel, characterLabelConstraints);

        // Add the character panel to the main panel with GridBagConstraints
        mainPanel.add(characterPanel, characterConstraints);

     // Panel to hold the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
        buttonPanelConstraints.gridx = 1;
        buttonPanelConstraints.gridy = 2;
        buttonPanelConstraints.weightx = 0.0;
        buttonPanelConstraints.weighty = 0.0;
        buttonPanelConstraints.fill = GridBagConstraints.NONE;
        buttonPanelConstraints.anchor = GridBagConstraints.CENTER;
        buttonPanelConstraints.insets = new Insets(20, 0, 0, 0); // Add vertical spacing
        mainPanel.add(buttonPanel, buttonPanelConstraints);

        // Hat button
        hatButton = createSettingsButton("hat");
        GridBagConstraints hatButtonConstraints = new GridBagConstraints();
        hatButtonConstraints.gridx = 0;
        hatButtonConstraints.gridy = 0;
        hatButtonConstraints.weightx = 0.0;
        hatButtonConstraints.weighty = 0.0;
        hatButtonConstraints.fill = GridBagConstraints.NONE;
        hatButtonConstraints.anchor = GridBagConstraints.CENTER;
        hatButtonConstraints.insets = new Insets(0, 10, 0, 10); // Add horizontal spacing
        buttonPanel.add(hatButton, hatButtonConstraints);

        // Boat button
        boatButton = createSettingsButton("boat");
        GridBagConstraints boatButtonConstraints = new GridBagConstraints();
        boatButtonConstraints.gridx = 0;
        boatButtonConstraints.gridy = 1;
        boatButtonConstraints.weightx = 0.0;
        boatButtonConstraints.weighty = 0.0;
        boatButtonConstraints.fill = GridBagConstraints.NONE;
        boatButtonConstraints.anchor = GridBagConstraints.CENTER;
        boatButtonConstraints.insets = new Insets(0, 10, 0, 10); // Add horizontal spacing
        buttonPanel.add(boatButton, boatButtonConstraints);

        // Reel button
        reelButton = createSettingsButton("reel");
        GridBagConstraints reelButtonConstraints = new GridBagConstraints();
        reelButtonConstraints.gridx = 0;
        reelButtonConstraints.gridy = 2;
        reelButtonConstraints.weightx = 0.0;
        reelButtonConstraints.weighty = 0.0;
        reelButtonConstraints.fill = GridBagConstraints.NONE;
        reelButtonConstraints.anchor = GridBagConstraints.CENTER;
        reelButtonConstraints.insets = new Insets(0, 10, 0, 10); // Add horizontal spacing
        buttonPanel.add(reelButton, reelButtonConstraints);

        // Back button (black arrow pointing to the left)
        ImageIcon backIcon = new ImageIcon("backArrow.png");
        backButton = new JLabel(backIcon);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setHorizontalAlignment(SwingConstants.LEFT); // Align to the left
        // Add an ActionListener to the backButton to handle the click event
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                // Code to switch back to the main menu screen goes here
                dispose(); // Close the current character setting screen
                if (mainMenu != null) {
                    mainMenu.setVisible(true); // Show the setting screen
                }
            }
        });
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 5;
        backButtonConstraints.weightx = 1.0;
        backButtonConstraints.anchor = GridBagConstraints.SOUTHWEST; // Align to the bottom left
        backButtonConstraints.insets = new Insets(10, 10, 10, 0); // Add some spacing
        mainPanel.add(backButton, backButtonConstraints);

        // Save button (smaller rounded edge rectangle with white bold letters inside reading “save”)
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 10));
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(50, 50));
        saveButton.setBorder(new LineBorder(Color.BLACK, 10, true));
        saveButton.addActionListener(new SaveButtonListener());
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        saveButtonConstraints.gridx = 2;
        saveButtonConstraints.gridy = 5;
        saveButtonConstraints.weightx = 1.0;
        saveButtonConstraints.anchor = GridBagConstraints.SOUTHEAST; // Align to the bottom right
        saveButtonConstraints.insets = new Insets(10, 0, 10, 10); // Add some spacing
        mainPanel.add(saveButton, saveButtonConstraints);

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);

        updateCharacterImage();
    }

    private JButton createSettingsButton(String category) {
        JButton button = new JButton(category.substring(0, 1).toUpperCase() + category.substring(1));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.decode("#bcbefa"));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBorder(new LineBorder(Color.decode("#f1eb9c"), 5, true));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        // Add hover effect to the button (change background color when hovered)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#8a8aff"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#bcbefa"));
            }
        });

        // Add a margin to the button text to center it better within the button bounds
        Insets margin = new Insets(0, 20, 0, 20);
        button.setMargin(margin);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the CharacterStateManager instance
                CharacterStateManager characterStateManager = CharacterStateManager.getInstance();

                // Update the default indices for the selected category
                if (category.equals("hat")) {
                	currentHatIndex = (currentHatIndex + 1) % 10;
                } else if (category.equals("boat")) {
                	currentBoatIndex = (currentBoatIndex + 1) % 10;
                } else if (category.equals("reel")) {
                	currentReelIndex = (currentReelIndex + 1) % 10;
                }
                
                updateCharacterImage();
            }
        });

        return button;
    }



    private void updateCharacterImage() {

    	// Retrieve the image arrays from the characterImages HashMap
        ImageIcon[] hatImages = characterImages.get("hat");
        ImageIcon womanImage = characterImages.get("woman")[0];
        ImageIcon[] boatImages = characterImages.get("boat");
        ImageIcon[] reelImages = characterImages.get("reel");

            // Get the size available for displaying the character image (100px square)
            int panelSize = 100;

            // Create a new ImageIcon 
            ImageIcon characterIcon = new ImageIcon(new BufferedImage(panelSize, panelSize, BufferedImage.TYPE_INT_ARGB));
            Graphics2D g2d = (Graphics2D) characterIcon.getImage().getGraphics();

            // Calculate the starting position to center the images within the 100px square
            int x = (panelSize - boatImages[currentBoatIndex].getIconWidth()) / 2;
            int y = (panelSize - boatImages[currentBoatIndex].getIconHeight()) / 2;

            // Draw the images at the calculated position
            g2d.drawImage(womanImage.getImage(), x, y, null); // Draw the woman first (behind the boat)
            g2d.drawImage(boatImages[currentBoatIndex].getImage(), x, y, null); // Draw the boat next

            // Draw the remaining images (hat and reel) on top of the woman and boat
            g2d.drawImage(hatImages[currentHatIndex].getImage(), x, y, null);
            g2d.drawImage(reelImages[currentReelIndex].getImage(), x, y, null);

            // Draw the black border around the images
            g2d.setColor(Color.decode("#f1eb9c"));
            g2d.drawRect(0, 0, panelSize - 1, panelSize - 1);
            g2d.drawRect(1, 1, panelSize - 3, panelSize - 3);

            g2d.dispose();

            // Set the character label to display the updated character image
            characterLabel.setIcon(characterIcon);
    }

    private class SaveButtonListener implements ActionListener {
    	 @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Get the CharacterStateManager instance
    	        CharacterStateManager characterStateManager = CharacterStateManager.getInstance();

    	        // Update the default indices in the CharacterStateManager
    	        characterStateManager.setDefaultHatIndex(currentHatIndex);
    	        characterStateManager.setDefaultBoatIndex(currentBoatIndex);
    	        characterStateManager.setDefaultReelIndex(currentReelIndex);

    	        // Display a message indicating that the character state is saved.
    	        JOptionPane.showMessageDialog(CharacterSettingScreen.this, "Character state saved!");
    	    }
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> new CharacterSettingScreen(null));
    }
}
