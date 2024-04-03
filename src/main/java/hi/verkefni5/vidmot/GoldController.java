package hi.verkefni5.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import vinnsla.Klukka;
import vinnsla.Leikur;

import java.util.HashMap;

public class GoldController {

    //viðmótstilviksbreytur

    @FXML
    private MenuController menuStyringController;

    @FXML
    private Label fxStig;

    @FXML
    private Label fxTimi;

    @FXML
    private Leikbord fxLeikbord;

    // Býr til beinan aðgang frá KeyCode og í heiltölu. Hægt að nota til að fletta upp
    // heiltölu fyrir KeyCode
    private static final HashMap<KeyCode, Stefna> map1 = new HashMap<KeyCode, Stefna>();

    //final tilviksbreytur
    public static final int INTERVAL = 100;

    //tilviksbreytur
    private Klukka klukka;
    private Leikur leikur = new Leikur();
    private int timi;
    private Timeline gullTimeline; // Tímalína fyrir Animation á gullinu
    private Timeline klukkuTimeline;
    private EventHandler<KeyEvent> hreyfing;

    /**
     * Þegar forritið er ræst fer þetta í gang
     * Leikborðið er vikrjað og leikur stilltur fyrir það
     * Tíminn er stilltur
     * Leikborðið fær fókus og er tengt við örvatakka fallið
     * Leikur hafinn og klukka ræst og stigin og tíminn eru tengd viðmót við property breytur
     */
    public void initialize() {
        menuStyringController.setGoldController(this);

        leikur = new Leikur();
        fxLeikbord.setFocusTraversable(true);
        fxLeikbord.setLeikur(leikur);
        stillaTima();
        Platform.runLater(() -> fxLeikbord.requestFocus());
        stillaHreyfingu();

        fxLeikbord.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                orvatakkar();
            }
        });
        hefjaLeik();
        fxStig.textProperty().bind(Bindings.concat("Stig: ", leikur.getStigProperty().asString()));

        raesaKlukku();
        fxTimi.textProperty().bind(Bindings.concat(klukka.getKlukkaProperty().asString(), " sek"));



    }


    /**
     * Orvatakkarnir stilltir og tengdir við leikborðið
     */
    public void orvatakkar() {
        map1.put(KeyCode.UP, Stefna.UPP);
        map1.put(KeyCode.DOWN, Stefna.NIDUR);
        map1.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map1.put(KeyCode.LEFT, Stefna.VINSTRI);

        Platform.runLater(() -> {
            Scene scene = fxLeikbord.getScene();
            if (scene != null) {
                scene.removeEventFilter(KeyEvent.ANY, hreyfing);
                scene.addEventFilter(KeyEvent.ANY, hreyfing);
                fxLeikbord.requestFocus();
            }
        });

    }

    /**
     * Leikur hafinn
     * Búinn til keyframe svo hægt sé að kalla á meiraGull aðferðina á sekúndu fresti
     * Ný tímalína búin til
     */
    public void hefjaLeik() {


        KeyFrame k = new KeyFrame(Duration.seconds(1),
                e -> {
                    fxLeikbord.meiraGull();
                    }
                );
        gullTimeline = new Timeline(k);
        gullTimeline.setCycleCount(Timeline.INDEFINITE);
        gullTimeline.play();
    }


    /**
     * klukka ræst og keyframe búinn til fyrir hana sem uppfærist á hveri sekúndu og kallar á tic fallið
     * Ný tímalína fer í gang sem er stöðvuð þegar tíminn er búinn og þá er kallað á leiklokið
     * Tímalínurnar keyrðar
     */
    public void raesaKlukku () {
        klukka = new Klukka(this.timi);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(INTERVAL),
                e -> {
                    klukka.tic();
                });


        if (klukkuTimeline != null) {
            klukkuTimeline.stop();
        }
        klukkuTimeline = new Timeline(keyFrame);    // búin til tímalína fyrir leikinn
        klukkuTimeline.setCycleCount(Timeline.INDEFINITE);
        klukkuTimeline.play();


        fxTimi.textProperty().bind(Bindings.concat(klukka.getKlukkaProperty().asString(), " sek"));

        klukka.getKlukkaProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() <= 0) {
                klukkuTimeline.stop();
                leikLokid();
            }
        });



    }

    /**
     * Þegar leikur klárast eru tímalínurnar stoppaðar og textinn er unbindaður svo hægt sé að setja nýjan texta,
     * klukkan og leikurinn stoppaður
     */
    private void leikLokid() {
        if (gullTimeline != null) {
            gullTimeline.stop();
        }
        if (klukkuTimeline != null) {
            klukkuTimeline.stop();
        }
        if (klukka != null) {
            klukka.stop();
        }
        if (fxTimi.textProperty().isBound()) {
            fxTimi.textProperty().unbind();
        }
        fxTimi.setText("Leik lokið");
        fxLeikbord.stoppaLeik();
    }

    /**
     * Tíminn fyrir klukkunu er upphafsstilltur eftir því erfiðleikastigi sem er valið
     */
    private void stillaTima() {
        this.timi = switch (menuStyringController.getRadioMenuItem().getText()) {
            case "Létt" -> 30;
            case "Miðlungs" -> 25;
            case "Erfitt" -> 20;
            default -> 0;
        };
    }

    /**
     * leikurinn endurræstur og kallað á aðferðir til að hreinsa borðið, upphafsstilla gullgrafara og stilla nýjan leik
     */
    public void endurraesa() {
        if (gullTimeline != null) {
            gullTimeline.stop();
        }
        if (klukkuTimeline != null) {
            klukkuTimeline.stop();
        }

        leikLokid();

        fxLeikbord.clear();
        fxLeikbord.upphafsstillaGrafara();

        leikur = new Leikur();
        fxLeikbord.setLeikur(leikur);
        fxLeikbord.raesaLeik();

        fxStig.textProperty().unbind();
        fxStig.setText("0");
        fxStig.textProperty().bind(Bindings.concat("Stig: ", leikur.getStigProperty().asString()));
        stillaTima();

        klukka.endurstillaTima(this.timi);
        klukka = new Klukka(timi);

        fxTimi.textProperty().unbind();
        fxTimi.textProperty().bind(Bindings.concat(klukka.getKlukkaProperty().asString(), " sek"));


        raesaKlukku();
        hefjaLeik();

        Platform.runLater(() -> {
            Scene scene = fxLeikbord.getScene();
            if (scene != null) {
                scene.removeEventFilter(KeyEvent.ANY, hreyfing);
                scene.addEventFilter(KeyEvent.ANY, hreyfing);
                fxLeikbord.requestFocus();
            }
        });

    }

    /**
     * hreyfingin stillt til að breyta stefnunni
     */
    private void stillaHreyfingu() {
        hreyfing = event -> {
            Stefna stefna = map1.get(event.getCode());
            if (stefna != null) {
                fxLeikbord.setStefna(stefna);
                fxLeikbord.afram();
            }
        };
    }

}
