package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {

    //porperty tilviksbreyta
    private  SimpleIntegerProperty stig = new SimpleIntegerProperty(0);

    /**
     * getter fyrir stig
     * @return stig
     */
    public final int getStig() {
        return stig.get();
    }

    /**
     * setter fyrir stig
     * @param value stig sem á að setja
     */
    public final void setStig(int value) {
        stig.set(value);
    }

    /**
     * getter fyrir stig
     * @return
     */
    public SimpleIntegerProperty getStigProperty() {
        return stig;
    }

    /**
     * hækka stigin um einn
     */
    public void haekkaStig() {
        this.stig.set(this.getStig() + 1);
    }



    public static void main(String[] args) {

    }
}
