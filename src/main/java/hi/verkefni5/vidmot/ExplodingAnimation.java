package hi.verkefni5.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ExplodingAnimation
{
    private ImageView mynd;
    int dalkur =0 ;
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
        switch (dalkur) {
            case 0:
                mynd.setViewport((new Rectangle2D(0, 0, 455, 480)));
                dalkur = 1;
                System.out.println(dalkur);
                break;
            case 1:
                mynd.setViewport((new Rectangle2D(455, 0, 455, 480)));
                dalkur = 2;

                System.out.println(dalkur);
                break;
            case 2:
                mynd.setViewport((new Rectangle2D(2 * 455, 0, 455, 480)));
                dalkur = 3;
                System.out.println(dalkur);
                break;
            case 3:
                mynd.setViewport((new Rectangle2D(3 * 455, 0, 455, 480)));
                dalkur = 4;
                System.out.println(dalkur);
                break;
            case 4:
                mynd.setViewport((new Rectangle2D(0, 480, 455, 480)));
                dalkur = 5;
                System.out.println(dalkur);
                break;
            case 5:
                mynd.setViewport((new Rectangle2D(455, 480, 455, 480)));
                dalkur = 6;
                System.out.println(dalkur);
                break;
            case 6:
                mynd.setViewport((new Rectangle2D(2 * 455, 480, 455, 480)));
                dalkur = 7;
                System.out.println(dalkur);
                break;
            case 7:
                mynd.setViewport((new Rectangle2D(3 * 455, 480, 455, 480)));
                System.out.println(dalkur);
                dalkur = 8;
                break;
            case 8:
                mynd.setViewport((new Rectangle2D(0, 0, 1, 1)));
                break;

        }
    }));
    public ExplodingAnimation(ImageView mynd){
        this.mynd =mynd;
        timeline.setCycleCount(9);
    }
    public void startAnimation(){
        timeline.play();
    }

    public void stopAnimation(){
        timeline.stop();
    }
}
