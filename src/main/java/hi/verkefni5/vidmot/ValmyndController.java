package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vinnsla.DifficultyModel;
import vinnsla.Hljodstillingar;

import java.io.IOException;

public class ValmyndController {

    @FXML
    public RadioButton fxHljod;
    //viðmótstilviksbreytur


    private KubbaKappController kubbaKappController;

    @FXML
    private MenuItem fxBreyta;

    @FXML
    private Button fxAfram;


    //togglegroup
    private ToggleGroup erfidleikastig = new ToggleGroup();

    //tilviksbreyta fyrir klasann
    private DifficultyModel difficultyModel;


    /**
     * Þegar valmyndin er opnuð er kveikt á listener á milli fxHljod takkans og hljóðsins í Hljóðstillingaklasanum
     */
    public void initialize() {
        fxHljod.selectedProperty().addListener((obs, varValid, erValid) -> {
            Hljodstillingar.getHljodstillingar().kveikjaAHljodi(erValid);
        });
    }

    /**
     * Þegar erfiðleikastig er valið
     */

    /**
     * Nýr leikur er hafinn
     */
    @FXML
    private void onNyrLeikur() {
        kubbaKappController.endurraesa();
    }


    @FXML
    private void onHaldaAfram() {
        if (this.kubbaKappController == null) {
            System.out.println("KubbaKappController er null");
            fxAfram.setText("Enginn leikur í gangi");
        } else {
            kubbaKappController.resume();
        }
    }


    /**
     * Alert dialog sem spyr hvort notandinn vilji hætta leik
     *
     * @param actionEvent ýtt á hætta
     */
    @FXML
    private void onHaetta(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hætta leik");
        alert.setHeaderText("Ertu viss um að þú viljir hætta að spila?");
        alert.setContentText("Veldu \"Hætta\" ef þú vilt hætta. \n Veldu \"Spila\" ef þú vilt spila áfram.");

        ButtonType buttonTypeQuit = new ButtonType("Hætta");
        ButtonType buttonTypeCancel = new ButtonType("Spila", ButtonType.CANCEL.getButtonData());
        alert.getButtonTypes().setAll(buttonTypeQuit, buttonTypeCancel);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeQuit) {
                System.exit(0);
            }
        });
    }


    /**
     * Alert dialog sem segir notanda frá forritinu
     *
     * @param actionEvent ýtt á um forritið
     */
    @FXML
    private void onUmForritid(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText("KubbaKapp");
        alert.setContentText("KubbaKapp er leikur þar sem tveir leikmenn keppast um að safna stigum á vissum tíma. \n" +
                "Tíminn fer eftir erfileikastigi (létt, miðlungs, erfitt) sem hægt er að velja í stillingum. \n" +
                "Hafa skal varann á sprengjum sem birtast af og til, ef leikmaður rekst á sprengju missir hann 1 af 3 lífum. " +
                "Ef öll líf klárast tapar sá hin sami leiknum. \n" + "Stig eru söfnuð með að ná kubbum, sem líkt og sprengjurnar birtast á leikskjánnum. \n" +
                "\n" + "Þegar sigri er náð birtist gluggi sem hefur valmöguleika á að hefja nýjan leik eða hætta.\n" +
                " \n" +
                "KubbaKappar smíðuðu þetta forrit.");
        alert.showAndWait();
    }

    /**
     * Birtir erfiðleikastigsvalmyndina og setur valið erfiðleikastig sem erfiðleikastigið í erfiðleikastigControllernum
     *
     * @throws IOException
     */
    @FXML
    private void onErfidleikastig() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("erfidleikastig-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        ErfidleikastigController erfidleikastigController = fxmlLoader.getController();
        erfidleikastigController.setDifficultyModel(this.difficultyModel);

        stage.show();
    }

    /**
     * setter
     *
     * @param kubbaKappController
     */
    public void setGoldController(KubbaKappController kubbaKappController) {
        this.kubbaKappController = kubbaKappController;
        Hljodstillingar.getHljodstillingar().kveikjaAHljodi(fxHljod.isSelected());
    }

    public static void main(String[] args) {

    }

}


