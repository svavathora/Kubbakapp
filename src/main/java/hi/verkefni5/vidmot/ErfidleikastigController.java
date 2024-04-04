package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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

    public void onErfidleikastig(ActionEvent actionEvent) {
       /* if (this.goldController == null) {
            System.out.println("goldController is null when onErfidleikastig is called");
        } else {
            RadioMenuItem valinnErfidleiki = ((RadioMenuItem) fxErfidleikastig.getSelectedToggle());
            goldController.erfidleiki(valinnErfidleiki.getId());
        }*/
    }

}
