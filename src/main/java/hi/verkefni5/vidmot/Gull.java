package hi.verkefni5.vidmot;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;


public class Gull extends Pane {

    /**
     * Smiður fyrir kassa, skráin lesinn inn og kassarnir smíðaðir í handahófskenndum lit
     */
    public Gull() {
        FXML_Lestur.lesa(this, "gull-view.fxml");
        this.setPrefSize(50, 50);

        Random random = new Random();
        int r = random.nextInt(75);
        int g = random.nextInt(1);
        int b = random.nextInt(256);
        Color randomColor = Color.rgb(r,g,b);

        this.setBackground(new Background(new BackgroundFill(randomColor, null, null)));
    }
    public void pickUpAnim(Grafari player){
        TranslateTransition position = new TranslateTransition();
        double xMidjaGulls = this.getLayoutX() + this.getWidth() / 2;
        double yMidjaGulls = this.getLayoutY() + this.getHeight() / 2;
        double xMidjaSpilara = player.getX() + player.getWidth() / 2;
        double yMidjaSpilara = player.getY() + player.getHeight() / 2;
        position.setToX(-xMidjaGulls + xMidjaSpilara);
        position.setToY(-yMidjaGulls + yMidjaSpilara);
        position.setDuration(Duration.millis(100));
        position.setCycleCount(1);
        FadeTransition fade = new FadeTransition();
        RotateTransition rotate = new RotateTransition();
        ScaleTransition scale = new ScaleTransition();
        position.setNode(this);
        scale.setNode(this);
        fade.setNode(this);
        rotate.setNode(this);
        scale.setByX(-0.9);
        scale.setDuration(Duration.millis(100));
        fade.setDuration(Duration.millis(100));
        rotate.setDuration(Duration.millis(100));
        scale.setCycleCount(1);
        rotate.setCycleCount(1);
        fade.setCycleCount(1);
        rotate.setAxis(Rotate.Z_AXIS);
        int angle = 40;
        if(xMidjaGulls < xMidjaSpilara) angle *= -1;
        if (yMidjaGulls > yMidjaSpilara) angle *= -1;
        rotate.setByAngle(angle);
        fade.setToValue(0);
        fade.play();
        rotate.play();
        scale.play();
        position.play();
    }

}