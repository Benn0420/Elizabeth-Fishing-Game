import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;

public class MainMenu extends JFrame {

    private JButton startButton;
    private JButton settingsButton;
    private JLabel highestScoreLabel;
    private Clip backgroundMusicClip;
    private static MainMenu instance;
    private boolean isBackgroundMusicLoaded = false;

    public static MainMenu getInstance() {
        return instance;
    }

    public MainMenu() {
        instance = this;

        // Frame setup
        setTitle("Elizabeth's Fishing Trip");
        setSize(540, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#bcb4ae")); // Set the background color to #bcb4ae

        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#f1eb9c"));
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
        JLabel titleLabel = new JLabel("Elizabeth's Fishing Trip");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, titleConstraints);

        // Add the title panel to the main panel at the top
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.weighty = 1.0; // Make it vertically stretchable
        mainConstraints.anchor = GridBagConstraints.NORTH; // Anchor at the top
        mainPanel.add(titlePanel, mainConstraints);

        // Start button
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 18)); // Make the font a bit smaller
        startButton.setBackground(Color.decode("#bcbefa"));
        startButton.setForeground(Color.BLACK); // Set the text color to black
        startButton.setFocusPainted(false); // Remove focus border
        startButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size
        startButton.setBorder(roundedBorder); // Set the rounded border
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose(); // Close the current main menu screen
                GameplayScreen gameplayScreen = new GameplayScreen(MainMenu.this);
                gameplayScreen.setVisible(true);
            }
        });
        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        startButtonConstraints.gridx = 0;
        startButtonConstraints.gridy = 1;
        startButtonConstraints.insets = new Insets(10, 0, 10, 0); // Add some spacing
        mainPanel.add(startButton, startButtonConstraints);

        // Settings button
        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Arial", Font.BOLD, 18)); // Make the font a bit smaller
        settingsButton.setBackground(Color.decode("#bcbefa"));
        settingsButton.setForeground(Color.BLACK); // Set the text color to black
        settingsButton.setFocusPainted(false); // Remove focus border
        settingsButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size
        settingsButton.setBorder(roundedBorder); // Set the rounded border
        // Add an ActionListener to the settingsButton to handle the click event
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to switch to the setting screen goes here
                dispose(); // Close the current main menu screen
                CharacterSettingScreen settingScreen = new CharacterSettingScreen(MainMenu.this); // Pass the current MainMenu instance to SettingScreen
                settingScreen.setVisible(true); // Show the setting screen
            }
        });
        GridBagConstraints settingsButtonConstraints = new GridBagConstraints();
        settingsButtonConstraints.gridx = 0;
        settingsButtonConstraints.gridy = 2;
        settingsButtonConstraints.insets = new Insets(0, 0, 20, 0); // Add some spacing
        mainPanel.add(settingsButton, settingsButtonConstraints);

     // Sun image
        ImageIcon sunIcon = new ImageIcon("sun.png");
        JLabel sunLabel = new JLabel(sunIcon);
        GridBagConstraints sunConstraints = new GridBagConstraints();
        sunConstraints.gridy = 3;
        sunConstraints.insets = new Insets(10, 0, 20, 0);
        mainPanel.add(sunLabel, sunConstraints);

        playBackgroundMusic();

        // Show the frame
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setVolume(float volume) {
        if (isBackgroundMusicLoaded) {
            FloatControl volumeControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }

    private void playBackgroundMusic() {
        if (isBackgroundMusicLoaded) {
            return; // Background music is already loaded and playing, so return early
        }

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    // Create an audio input stream for the "grasslands.wav" file
                    // Make sure the "grasslands.wav" file is in the same directory as the .java files
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MainMenu.class.getResourceAsStream("grasslands.wav"));

                    // Get a clip to play the audio
                    backgroundMusicClip = AudioSystem.getClip();
                    backgroundMusicClip.open(audioInputStream);

                    // Set the volume to a moderate level (ranged from 0 to 1)
                    setVolume(1);

                    // Loop the audio to play indefinitely
                    backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

                    // Mark the background music as loaded and initialized
                    isBackgroundMusicLoaded = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}


