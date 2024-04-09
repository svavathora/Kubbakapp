package vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Erfidleikaval {

    //tilviksbreytur
    private static final Erfidleikaval valNotanda = new Erfidleikaval();

    private StringProperty erfidleiki = new SimpleStringProperty("Auðvelt"); // Default difficulty

    private Erfidleikaval() {}

    /**
     * getter fyrir val notanda
     * @return
     */
    public static Erfidleikaval getValNotanda() {
        return valNotanda;
    }

    /**
     * getter fyrir valinn erfiðleika
     * @return
     */
    public String getErfidleiki() {
        return erfidleiki.get();
    }

    /**
     * setter fyrir valinn erfiðleika
     * @param erfidleiki
     */
    public void setErfidleiki(String erfidleiki) {
        this.erfidleiki.set(erfidleiki);
    }

    /**
     * skilar Property fyrir erfiðleikann
     * @return
     */
    public StringProperty erfidleikiProperty() {
        return erfidleiki;
    }
}
