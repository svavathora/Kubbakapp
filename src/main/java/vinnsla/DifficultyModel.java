package vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DifficultyModel {
    private static final DifficultyModel instance = new DifficultyModel();

    private StringProperty difficulty = new SimpleStringProperty("Auðvelt"); // Default difficulty

    // Private constructor prevents instantiation from outside the class
    private DifficultyModel() {}

    // Public method to access the singleton instance
    public static DifficultyModel getInstance() {
        return instance;
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }
}
