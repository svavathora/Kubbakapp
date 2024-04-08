package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vinnsla.DifficultyModel;

import java.io.IOException;

public class ValmyndController {

    //viðmótstilviksbreytur
    @FXML
    private GoldController goldController;

    @FXML
    private MenuItem fxBreyta;

    @FXML
    private Button fxAfram;

    //togglegroup
    private ToggleGroup erfidleikastig = new ToggleGroup();
    private DifficultyModel difficultyModel;

    /**
     * Þegar erfiðleikastig er valið
     */

    /**
     * Nýr leikur er hafinn
     */
    @FXML
    private void onNyrLeikur() {
        goldController.endurraesa();
    }

    @FXML
    private void onHaldaAfram(){
        if(this.goldController == null){
            System.out.println("GoldController er null");
            fxAfram.setText("Enginn leikur í gangi");
        }
        else {
            goldController.resume();
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
        // Now set the shared DifficultyModel on ErfidleikastigController
        erfidleikastigController.setDifficultyModel(this.difficultyModel); // Assuming you have a getter for difficultyModel in ValmyndController

        stage.show();
    }

    /**
     * setter
     * @param goldController
     */
    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    public static void main(String[] args) {

    }

}


