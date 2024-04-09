package vinnsla;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Hljodstillingar {

    //tilviksbreytur
    private static final Hljodstillingar hljodstillingar = new Hljodstillingar();

    //default er að kveikt sé á hljóðinu
    private BooleanProperty hljodKveikt = new SimpleBooleanProperty(true);

    /**
     * tómur smiður
     */
    private Hljodstillingar() {}

    /**
     * Getter fyrir tilviksbreytuna hljodstillingar
     * @return
     */
    public static Hljodstillingar getHljodstillingar() {
        return hljodstillingar;
    }

    /**
     * Getter fyrir boolean breytuna erHljodKveikt sem segir til um hvort kveikt sé á hljóðinu
     * @return true ef kveikt er á hljóðinu
     */
    public boolean erHljodKveikt() {
        return hljodKveikt.get();
    }

    /**
     * Setter fyrir boolean breytuna erHljodKveikt sem segir til um hvort kveikt sé á hljóðinu
     * @param hljodKveikt segir true ef kveikt er á hljóðinu
     */
    public void kveikjaAHljodi(boolean hljodKveikt) {
        this.hljodKveikt.set(hljodKveikt);
    }

    /**
     * Skilar gildinu á boolean property breytunni hljodkveikt
     * @return skilar true ef kveikt er á hljóðinu
     */
    public BooleanProperty hljodKveiktProperty() {
        return hljodKveikt;
    }
}
