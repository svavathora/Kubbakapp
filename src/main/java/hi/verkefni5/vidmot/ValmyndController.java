package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vinnsla.Erfidleikaval;

import java.io.IOException;

public class ValmyndController {

    //viðmótstilviksbreytur
    //@FXML
    private GoldController goldController;

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
     * Þegar erfiðleikastig er valið
     */

    /**
     * Nýr leikur er hafinn
     */
    @FXML
    private void onNyrLeikur(ActionEvent actionEvent) throws IOException {
        try {
            GoldController.getInstance().endurraesa();//ef það er leikur í gangi

            lokaNuverandiGlugga(actionEvent);
        } catch (IllegalStateException e) {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("goldrush-view.fxml"));
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


    @FXML
    private void onHaldaAfram(ActionEvent actionEvent){
        try {
            GoldController.getInstance().resume();
            lokaNuverandiGlugga(actionEvent);
        } catch (IllegalStateException e) {
            fxAfram.setText("Enginn leikur í gangi");
        }
    }

    /**
     *Alert dialog sem spyr hvort notandinn vilji hætta leik
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
     * @param actionEvent ýtt á um forritið
     */
    @FXML
    private void onUmForritid(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText("Gullgrafaraleikur");
        alert.setContentText("Hreyfðu gullgrafarann og grafðu eftir gulli til að safna stigum. \n" +
                "Hægt er að stilla erfiðleikastig undir \"Breyta\". \n" +
                "Hægt er að hefja nýjan leik eða hætta undir \"Skrá\". \n" +
                "KubbaKappar smíðuðu þetta forrit.");


        alert.showAndWait();
    }

    @FXML
    private void onErfidleikastig() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("erfidleikastig-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        ErfidleikastigController erfidleikastigController = fxmlLoader.getController();
        erfidleikastigController.setDifficultyModel(this.erfidleikaval);
        stage.show();
    }

    @FXML
    private void onTilBaka(ActionEvent actionEvent){
        Stage nuverandiStage = (Stage) fxTilBaka.getScene().getWindow();
        nuverandiStage.close();
    }

    private void lokaNuverandiGlugga(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource();
        Stage currentStage = (Stage) sourceButton.getScene().getWindow();
        currentStage.close();
    }

    public static void main(String[] args) {

    }

}


