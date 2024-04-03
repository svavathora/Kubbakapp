package hi.verkefni5.vidmot;

public enum Stefna {
    VINSTRI(180),
    HAEGRI(360),
    NIDUR (270),
    UPP (90);


    //tilviskbreyta
    private final int gradur;

    /**
     * Smiður fyrir stefnuna
     * @param s gradur
     */
    Stefna(int s) {
        gradur = s;
    }

}
