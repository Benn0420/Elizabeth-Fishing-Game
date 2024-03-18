
public class CharacterStateManager {
	
	private static CharacterStateManager instance;

    // Variables to hold the default image array indices for each character component
    private int defaultHatIndex = 0;
    private int defaultBoatIndex = 0;
    private int defaultReelIndex = 0;

    // Private constructor to prevent direct instantiation
    private CharacterStateManager() {}

    // Static method to get the single instance of the class
    public static CharacterStateManager getInstance() {
        if (instance == null) {
            instance = new CharacterStateManager();
        }
        return instance;
    }
    
    // Getter for the defaultHatIndex
    public int getDefaultHatIndex() {
        return defaultHatIndex;
    }

    // Setter for the defaultHatIndex
    public void setDefaultHatIndex(int defaultHatIndex) {
        this.defaultHatIndex = defaultHatIndex;
    }

    // Getter for the defaultBoatIndex
    public int getDefaultBoatIndex() {
        return defaultBoatIndex;
    }

    // Setter for the defaultBoatIndex
    public void setDefaultBoatIndex(int defaultBoatIndex) {
        this.defaultBoatIndex = defaultBoatIndex;
    }

    // Getter for the defaultReelIndex
    public int getDefaultReelIndex() {
        return defaultReelIndex;
    }

    // Setter for the defaultReelIndex
    public void setDefaultReelIndex(int defaultReelIndex) {
        this.defaultReelIndex = defaultReelIndex;
    }

}
