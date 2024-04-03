package hi.verkefni5.vidmot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;


public class Gull extends Pane {

    private ImageView imageView;

    /**
     * Smiður fyrir gullmola, skráin lesinn inn og gullið smíðað
     */
    public Gull() {
        FXML_Lestur.lesa(this,"gull-view.fxml" );

        this.setPrefSize(50, 50);


        imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


        URL url = getClass().getResource("/media/mynd2.png");
        if (url != null) {
            Image image = new Image(url.toExternalForm());
            imageView.setImage(image);
        } else {
            System.err.println("Skráin fannst ekki: " + "/media/mynd2.png");
        }

        this.getChildren().add(imageView);

    }

    public static void main(String[] args) {

    }
}
