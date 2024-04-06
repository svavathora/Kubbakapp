package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class ErfidleikastigController {
    @FXML
    private GoldController goldController;

    @FXML
    private RadioButton stig1;

    @FXML
    private RadioButton stig2;

    @FXML
    private RadioButton stig3;

    @FXML
    private ToggleGroup fxErfidleikastig;

    @FXML
    private Button fxTilBaka;

    public void initialize() {
        //
    }

    //@FXML
    /*private void onErfidleikastig() {
        RadioMenuItem validItem = (RadioMenuItem) fxErfidleikastig.getSelectedToggle();
    }*/

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }
    @FXML
    public void onErfidleikastig(ActionEvent actionEvent) {
       /* if (this.goldController == null) {
            System.out.println("goldController is null when onErfidleikastig is called");
        } else {
            RadioMenuItem valinnErfidleiki = ((RadioMenuItem) fxErfidleikastig.getSelectedToggle());
            goldController.erfidleiki(valinnErfidleiki.getId());
        }*/
    }

    @FXML
    private void onTilBaka(ActionEvent actionEvent) throws IOException {
        Stage nuverandiStage = (Stage) fxTilBaka.getScene().getWindow();
        nuverandiStage.close();
    }



}
