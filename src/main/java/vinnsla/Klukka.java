package vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Klukka {

    //property tilviksbreyta
    private SimpleIntegerProperty klukkaProperty;
    private Timeline timeline;


    /**
     * Klukkan búin til
     *
     * @param startTimi upphafstíminn stilltur
     */
    public Klukka(int startTimi) {

        klukkaProperty = new SimpleIntegerProperty(startTimi);

        //kallað á tic
        EventHandler<ActionEvent> eventHandler = e -> {
            tic();
        };

        // Búum til timalinu sem uppfærist á hverri sekúndu og gengur endalaust
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), eventHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Start animation
    }


    /**
     * Klukkan er minnkuð um 1 þangað til tíminn er búinn
     */
    public void tic() {
        if (klukkaProperty.get() > 0) {
            klukkaProperty.set(klukkaProperty.get() - 1);
        } else {
            stop();
            System.out.println("Tíminn er búinn");

        }
    }



    /**
     * getter fyrir klukku
     * @return klukka
     */
    public SimpleIntegerProperty getKlukkaProperty() {
        return klukkaProperty;
    }

    /**
     * endurstilla tima
     * @param nyrTimi nýr tími
     */
    public void endurstillaTima(int nyrTimi) {
        klukkaProperty.set(nyrTimi);
    }

    /**
     * tímalínan stoppuð
     */
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void pause(){
        if(timeline!=null){
            timeline.pause();
        }
    }

    public void resume(){
        if(timeline!=null){
            timeline.play();
        }
    }

    public static void main(String[] args) {
        }

}
