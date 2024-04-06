package hi.verkefni5.vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import vinnsla.Leikur;

public class Leikbord extends Pane {

    //tilviksbreytur
    private Leikur leikur;
    private boolean leikurIGangi = true;
    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();

    @FXML
    private Grafari fxGrafari;

    /**
     * Smiðurinn, fxml skráin lesin inn
     */
    public Leikbord() {
        System.out.println(this.idProperty());
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
    }

    /**
     * Setter fyrir leik
     * @param leikur leikur
     */
    public void setLeikur(Leikur leikur) {
        this.leikur = leikur;
    }

    /**
     * Kallar á framleidaGull
     */
    public void meiraGull() {
        if(!leikurIGangi) {
            return;
        }
        framleidaGull();
    }

    /**
     * Kallar á setterinn fyrir grafarann og svo áfram fallið
     * @param stefna stefna
     */
    public void setStefna(Stefna stefna) {
        fxGrafari.setStefna(stefna);
        afram();
    }

    /**
     * Færir leikmann áfram í þá átt sem stefnan var sett
     */
    public void afram() {
        final double stepSize = 10;
        if(!leikurIGangi) {
            return;
        }

        if (this.leikur != null) {
            double newX = fxGrafari.getLayoutX();
            double newY = fxGrafari.getLayoutY();

            switch (fxGrafari.getStefna()) {
                case UPP:
                    newY -= stepSize;
                    break;
                case NIDUR:
                    newY += stepSize;
                    break;
                case VINSTRI:
                    newX -= stepSize;
                    break;
                case HAEGRI:
                    newX += stepSize;
                    break;
            }

            // Mörkin
            double maxWidth = this.getWidth();
            double maxHeight = this.getHeight();

            // Passa mörkin
            newX = Math.max(0, Math.min(newX, maxWidth - fxGrafari.getWidth()));
            newY = Math.max(0, Math.min(newY, maxHeight - fxGrafari.getHeight()));

            fxGrafari.setLayoutX(newX);
            fxGrafari.setLayoutY(newY);

            if (erGrefurGull()) {
                leikur.haekkaStig();
                arekstur();
            }
        }
    }

    /**
     * Stillir boolean gildið svo að leikurinn svo stopp
     */
    public void stoppaLeik() {
        leikurIGangi = false;
    }

    /**
     * Stillir boolean gildið svo leikurinn sé í gangi
     */
    public void raesaLeik() {
        leikurIGangi = true;
    }


    /**
     * Athugað hvort grafari rekist á gull
     * @return true ef grafari er að rekast á gull, annars false
     */
    public boolean erGrefurGull() {
        for (Gull gull : gullListi) {
            if (fxGrafari.getBoundsInParent().intersects(gull.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Nýtt gull er búið til og birt á slembnum stað, því er bætt í observable listann
     */
    private void framleidaGull() {
        Gull g = new Gull();

        double gullWidth = 50.0;
        double gullHeight = 50.0;

        double maxX = this.getWidth() - gullWidth;
        double maxY = this.getHeight() - gullHeight;

        double randomX = Math.random() * maxX;
        double randomY = Math.random() * maxY;

        g.setLayoutX(randomX);
        g.setLayoutY(randomY);

        this.getChildren().add(g);
        gullListi.add(g);
    }

    /**
     * Fjarlægir gullið ef grafarinn rekst á það
     */
    private void arekstur() {
        for (Gull gull : gullListi) {
            if (fxGrafari.getBoundsInParent().intersects(gull.getBoundsInParent())) {
                getChildren().remove(gull);
                gullListi.remove(gull);
                break;
            }
        }
    }


    /**
     * fjarlægir gullmola af borðinu
     */
    public void clear() {
        getChildren().removeIf(node -> node instanceof Gull);
        gullListi.clear();
    }

    /**
     * setur grafara í upphafsstöðu
     */
    public void upphafsstillaGrafara() {
        if(fxGrafari != null) {
            fxGrafari.setLayoutX(this.getWidth());
            fxGrafari.setLayoutY(this.getHeight());
        }
    }

}
