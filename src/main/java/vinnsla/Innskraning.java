package vinnsla;

public class Innskraning {

    //Tilviksbreytur
    private String nafn1;
    private String nafn2;

    /**
     *Smiður fyrir notendur leiksins
     * @param nafn1
     * @param nafn2
     */
    public Innskraning(String nafn1, String nafn2) {
        this.nafn1 = nafn1;
        this.nafn2 = nafn2;
    }

    /**
     * Getter fyrir leikmann 1
     * @return nafn1
     */
    public String getNafn1() {
        return nafn1;
    }

    /**
     * Getter fyrir leikmann 2
     * @return nafn2
     */
    public String getNafn2() {
        return nafn2;
    }

    /**
     * Setter fyrir leikmann 1
     * @param nafn1
     */
    public void setNafn1(String nafn1) {
        this.nafn1 = nafn1;
    }

    /**
     * Setter fyrir leikmann 2
     * @param nafn2
     */
    public void setNafn2(String nafn2) {
        this.nafn2 = nafn2;
    }

    public static void main(String[] args) {

    }
}
