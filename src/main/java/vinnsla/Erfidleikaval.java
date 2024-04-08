package vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Erfidleikaval {
    private static final Erfidleikaval valNotanda = new Erfidleikaval();

    private StringProperty erfidleiki = new SimpleStringProperty("Auðvelt"); // Default difficulty

    // Private constructor prevents instantiation from outside the class
    private Erfidleikaval() {}

    // Public method to access the singleton instance
    public static Erfidleikaval getValNotanda() {
        return valNotanda;
    }

    public String getErfidleiki() {
        return erfidleiki.get();
    }

    public void setErfidleiki(String erfidleiki) {
        this.erfidleiki.set(erfidleiki);
    }

    public StringProperty erfidleikiProperty() {
        return erfidleiki;
    }
}
