package hi.verkefni5.vidmot;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class Sprengja extends Pane {

    private ImageView imageView;
    int dalkur = 0;

    /**
     * Smiður fyrir sprengju, skráin lesinn inn og sprengjan smíðað
     */
    public Sprengja() {
        FXML_Lestur.lesa(this, "sprengja-view.fxml");

        this.setPrefSize(40, 40);


        imageView = new ImageView();
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);


        URL url = getClass().getResource("/media/bomb.png");
        assert url != null;
        Image bomba = new Image(url.toExternalForm());
        imageView.setImage(bomba);
        this.getChildren().add(imageView);

    }
    public void boom(){
        URL url2 = getClass().getResource("/media/explosion.png");
        assert url2 != null;
        Image explosion = new Image(url2.toExternalForm());
        imageView.setImage(explosion);
        imageView.setViewport((new Rectangle2D(0, 480, 455, 480)));
        ExplodingAnimation explode = new ExplodingAnimation(imageView);
        explode.startAnimation();
    }

}
