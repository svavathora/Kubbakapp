package hi.verkefni5.vidmot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class Grafari extends Pane {

    // TIlviksbreytur
    private Stefna nuverandiStefna;
    private ImageView imageView;
    private Image blueUp = getImage("/media/blueUp.png");
    private Image blueDown = getImage("/media/blueDown.png");
    private Image blueRight = getImage("/media/blueRight.png");
    private Image blueLeft = getImage("/media/blueLeft.png");


    /**
     * Smiður fyrir grafara, myndin sett og stærð og staðsetning
     */
    public Grafari() {
        FXML_Lestur.lesa(this,"grafari-view.fxml");
        this.setPrefSize(50, 50);
        imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setImage(blueUp);

        this.getChildren().add(imageView);
        this.setLayoutX(50);
        this.setLayoutY(50);

    }

    /**
     * Fall sem skilar url-i myndar á réttu formi
     * @param urlS hlekkur myndarinnar
     * @return hlekki á réttu formi
     */
    public Image getImage(String urlS){
        URL url = getClass().getResource(urlS);
        assert url != null;
        return new Image(url.toExternalForm());
    }

    /**
     * Setter fyrir stefnu
     * @param stefna
     */

    public void setStefna(Stefna stefna) {
        switch (stefna){
            case UPP -> imageView.setImage(blueUp);
            case NIDUR -> imageView.setImage(blueDown);
            case HAEGRI -> imageView.setImage(blueRight);
            case VINSTRI -> imageView.setImage(blueLeft);
        }
        nuverandiStefna = stefna;
    }

    /**
     * Getter fyrir stefnu
     * @return stefna
     */
    public Stefna getStefna() {
        return nuverandiStefna;
    }


    /**
     * Myndinni er snúið í rétta stefnu
     * @param stefna stefna sem snúa á mynd í
     */
    public void myndirSamkvæmtStefnu(Stefna stefna) {
        switch (stefna) {
            case UPP -> imageView.setImage(blueUp);
            case NIDUR -> imageView.setImage(blueDown);
            case HAEGRI -> imageView.setImage(blueRight);
            case VINSTRI -> imageView.setImage(blueLeft);
        }
    }

    public double getX() {
        return this.getLayoutX();
    }
    public double getY() {
        return this.getLayoutY();
    }
}
