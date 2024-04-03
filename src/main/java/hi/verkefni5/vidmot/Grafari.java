package hi.verkefni5.vidmot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class Grafari extends Pane {

    // TIlviksbreytur
    private Stefna nuverandiStefna;
    private ImageView imageView;

    /**
     * Smiður fyrir grafara, myndin sett og stærð og staðsetning
     */
    public Grafari() {
        FXML_Lestur.lesa(this,"grafari-view.fxml");


        this.setPrefSize(100, 100);


        imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);


        URL url = getClass().getResource("/media/mynd.png");
        if (url != null) {
            Image image = new Image(url.toExternalForm());
            imageView.setImage(image);
        } else {
            System.err.println("Skráin fannst ekki: " + "/media/mynd.png");
        }

        this.getChildren().add(imageView);
        this.setLayoutX(450);
        this.setLayoutY(325);

    }

    /**
     * Setter fyrir stefnu
     * @param stefna
     */
    public void setStefna(Stefna stefna) {

        nuverandiStefna = stefna;
    }

    /**
     * Getter fyrir stefnu
     * @return stefna
     */
    public Stefna getStefna() {
        return nuverandiStefna;
    }

    public static void main(String[] args) {

    }
}
