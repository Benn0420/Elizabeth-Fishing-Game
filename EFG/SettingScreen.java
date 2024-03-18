import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingScreen extends JFrame {

	// Define components for the settings screen
    private JButton characterButton;
    private JLabel musicLabel;
    private JCheckBox musicCheckBox;
    private JLabel volumeLabel;
    private JProgressBar volumeProgressBar;
    private JLabel backButton;
    private int initialPosition;
    private boolean isLeftClicking;
    private MainMenu mainMenu;
    
    public SettingScreen(MainMenu mainMenu) {
    	this.mainMenu = mainMenu;
    	
    	// Frame setup
        setTitle("Elizabeth's Fishing Trip - Settings");
        setSize(540, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#bcb4ae")); // Set the background color to #bcb4ae

        // Main panel with GridBagLayout for holding buttons and components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        getContentPane().add(mainPanel);
        
        // Creating a rounded border with padding for bubble-like appearance
        int padding = 5;
        LineBorder roundedBorder = new LineBorder(Color.decode("#f1eb9c"), padding, true);

        // Title label panel with white background and rounded border
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE); // Set the background color to white
        titlePanel.setBorder(roundedBorder); // Set the rounded border
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(10, 10, 10, 10); // Add some spacing

        // Title label
        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, titleConstraints);

        // Add the title panel to the main panel at the top
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.weightx = 1.0;
        mainConstraints.anchor = GridBagConstraints.NORTH; // Anchor at the top
        mainPanel.add(titlePanel, mainConstraints);

        // Character button
        characterButton = new JButton("Character");
        characterButton.setFont(new Font("Arial", Font.BOLD, 18));
        characterButton.setBackground(Color.decode("#bcbefa"));
        characterButton.setForeground(Color.BLACK);
        characterButton.setFocusPainted(false);
        characterButton.setPreferredSize(new Dimension(150, 50));
        characterButton.setBorder(roundedBorder); // Set the rounded border
        // Add an ActionListener to the characterButton to handle the click event
        //characterButton.addActionListener(new ActionListener() {
        	//@Override
        	//public void actionPerformed (ActionEvent e) {
        		// Code to switch to the character screen goes here
                //dispose(); // Close the current setting screen
                //CharacterSettingScreen characterSettingScreen = new CharacterSettingScreen(SettingScreen.this); // Pass the current SettingScreen instance to CharacterSettingScreen
                //characterSettingScreen.setVisible(true); // Show the character setting screen
        	//}
        //});
        GridBagConstraints characterButtonConstraints = new GridBagConstraints();
        characterButtonConstraints.gridx = 0;
        characterButtonConstraints.gridy = 1;
        characterButtonConstraints.insets = new Insets(40, 0, 10, 0);
        mainPanel.add(characterButton, characterButtonConstraints);

        // Music label
        musicLabel = new JLabel("Music (on/off)");
        musicLabel.setFont(new Font("Arial", Font.BOLD, 18));
        musicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        musicLabel.setBackground(Color.decode("#bcbefa"));
        musicLabel.setForeground(Color.BLACK);
        musicLabel.setOpaque(true);
        musicLabel.setPreferredSize(new Dimension(150, 50));
        musicLabel.setBorder(roundedBorder); // Set the rounded border
        GridBagConstraints musicLabelConstraints = new GridBagConstraints();
        musicLabelConstraints.gridx = 0;
        musicLabelConstraints.gridy = 2;
        musicLabelConstraints.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(musicLabel, musicLabelConstraints);

        // Music on/off check box
        musicCheckBox = new JCheckBox();
        musicCheckBox.setFocusPainted(false);
        GridBagConstraints musicCheckBoxConstraints = new GridBagConstraints();
        musicCheckBoxConstraints.gridx = 0;
        musicCheckBoxConstraints.gridy = 3;
        musicCheckBoxConstraints.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(musicCheckBox, musicCheckBoxConstraints);

        // Volume label
        volumeLabel = new JLabel("Volume");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        volumeLabel.setBackground(Color.decode("#bcbefa"));
        volumeLabel.setForeground(Color.BLACK);
        volumeLabel.setOpaque(true);
        volumeLabel.setPreferredSize(new Dimension(100, 50));
        volumeLabel.setBorder(roundedBorder); // Set the rounded border
        GridBagConstraints volumeLabelConstraints = new GridBagConstraints();
        volumeLabelConstraints.gridx = 0;
        volumeLabelConstraints.gridy = 4;
        volumeLabelConstraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(volumeLabel, volumeLabelConstraints);

        // Volume control using JProgressBar
        volumeProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        volumeProgressBar.setValue(50);
        volumeProgressBar.setStringPainted(false); // Set to false to enable mouse events
        volumeProgressBar.setBackground(Color.WHITE);
        volumeProgressBar.setForeground(Color.decode("#bcbefa"));
        volumeProgressBar.setPreferredSize(new Dimension(100, 50));
        volumeProgressBar.setBorder(roundedBorder); // Set the rounded border
        GridBagConstraints volumeProgressBarConstraints = new GridBagConstraints();
        volumeProgressBarConstraints.gridx = 0;
        volumeProgressBarConstraints.gridy = 5;
        volumeProgressBarConstraints.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        volumeProgressBarConstraints.insets = new Insets(0, 10, 10, 10); // Add some spacing
        mainPanel.add(volumeProgressBar, volumeProgressBarConstraints);

        // Add a MouseListener to the volumeProgressBar for volume adjustment
        volumeProgressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) { // Check for left-click
                    initialPosition = e.getX();
                    isLeftClicking = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) { // Check for left-click
                    isLeftClicking = false;
                }
            }
        });

        // Add a MouseMotionListener to the volumeProgressBar for continuous volume adjustment
        volumeProgressBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isLeftClicking) {
                    int progress = volumeProgressBar.getValue();
                    int newProgress = Math.max(0, Math.min(100, progress + (e.getX() - initialPosition)));
                    volumeProgressBar.setValue(newProgress);
                    initialPosition = e.getX(); // Update the initial position for smoother dragging
                }
            }
        });

        // Back button (black arrow pointing to the left)
        ImageIcon backIcon = new ImageIcon("D:\\AlgonquinCollege\\Eclipse\\WorkspaceDirectory\\EFG\\backArrow.png");
        backButton = new JLabel(backIcon);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setHorizontalAlignment(SwingConstants.LEFT); // Align to the left
        // Add an ActionListener to the backButton to handle the click event
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                // Code to switch back to the main menu screen goes here
                dispose(); // Close the current setting screen
                if (mainMenu != null) {
                    mainMenu.setVisible(true); // Show the main menu screen
                }
            }
        });
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 6;
        backButtonConstraints.weightx = 1.0;
        backButtonConstraints.anchor = GridBagConstraints.SOUTHWEST; // Align to the bottom left
        backButtonConstraints.insets = new Insets(10, 10, 10, 0); // Add some spacing
        mainPanel.add(backButton, backButtonConstraints);

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);
    	
    }
    
    public static void main(String[] args) {
    	 
    	SwingUtilities.invokeLater(() -> new SettingScreen(null));
    	
    }
	
}
