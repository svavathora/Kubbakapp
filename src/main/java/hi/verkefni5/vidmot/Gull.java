package hi.verkefni5.vidmot;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;


public class Gull extends Pane {

    private ImageView imageView;

    /**
     * Smiður fyrir kassa, skráin lesinn inn og kassarnir smíðaðir í handahófskenndum lit
     */
    public Gull() {
        FXML_Lestur.lesa(this, "gull-view.fxml");
        this.setPrefSize(50, 50);

        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color randomColor = Color.rgb(r,g,b);

        this.setBackground(new Background(new BackgroundFill(randomColor, null, null)));
    }

}