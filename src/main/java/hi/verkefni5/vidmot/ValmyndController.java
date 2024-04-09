package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vinnsla.Erfidleikaval;
import vinnsla.Hljodstillingar;
import java.io.IOException;

public class ValmyndController {

    @FXML
    public RadioButton fxHljod;

    private KubbaKappController kubbaKappController;

    @FXML
    private Button fxNyrLeikur;

    @FXML
    private Button fxAfram;

    @FXML
    private Button fxTilBaka;

    //togglegroup
    private ToggleGroup erfidleikastig = new ToggleGroup();
    private Erfidleikaval erfidleikaval;


    /**
     * Þegar valmyndin er opnuð er kveikt á listener á milli fxHljod takkans og hljóðsins í Hljóðstillingaklasanum
     */
    public void initialize() {
        boolean erKveikt = Hljodstillingar.getHljodstillingar().erHljodKveikt();
        fxHljod.setSelected(erKveikt);

        fxHljod.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            Hljodstillingar.getHljodstillingar().kveikjaAHljodi(isSelected);
            if (isSelected) {
                KubbaKappController.getInstance().spilaLag();
            } else {
                KubbaKappController.getInstance().stoppaLag();
            }
        });
    }


    /**
     * Nýr leikur er hafinn
     */
    @FXML
    private void onNyrLeikur(ActionEvent actionEvent) throws IOException {
        try {
            KubbaKappController.getInstance().endurraesa();//ef það er leikur í gangi

            lokaNuverandiGlugga(actionEvent);
        } catch (IllegalStateException e) {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("goldrush-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                stage.setTitle("KubbaKapp");
                stage.setScene(scene);
                stage.show();
            } catch (IOException a) {
                e.printStackTrace();
            }
            lokaNuverandiGlugga(actionEvent);
        }
    }


    /**
     * Leikurinn ræstur aftur ef hann var í gangi, annars birtist viðeigandi tilkynning á takkanum
     * @param actionEvent ýtt á halda áfram
     */
    @FXML
    private void onHaldaAfram(ActionEvent actionEvent){
        try {
            KubbaKappController.getInstance().resume();
            lokaNuverandiGlugga(actionEvent);
        } catch (IllegalStateException e) {
            fxAfram.setText("Enginn leikur í gangi");
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
                "Ef öll líf klárast tapar sá leikmaður leiknum. \n" + "Stigum eru safnað með því að ná kubbum sem, líkt og sprengjurnar, birtast á leikskjánnum. \n" +
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
        erfidleikastigController.setDifficultyModel(this.erfidleikaval);
        stage.show();
    }

    /**
     * Leikurinn ræstur aftur ef hann var í gangi, annars færist notandi aftur á heimasvæði
     * @param actionEvent ýtt á halda áfram
     */
    @FXML
    private void onTilBaka(ActionEvent actionEvent){
        if (KubbaKappController.getInstance() != null) {
            KubbaKappController.getInstance().resume();
            lokaNuverandiGlugga(actionEvent);
        } else {
            lokaNuverandiGlugga(actionEvent);
        }
    }


    /**
     * Lokar núverandi glugga
     * @param actionEvent
     */
    private void lokaNuverandiGlugga(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource();
        Stage currentStage = (Stage) sourceButton.getScene().getWindow();
        currentStage.close();
    }

    public static void main(String[] args) {

    }

}


