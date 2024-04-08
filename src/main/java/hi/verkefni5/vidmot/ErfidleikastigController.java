package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import vinnsla.DifficultyModel;

import java.io.IOException;

public class ErfidleikastigController {

    @FXML
    private ToggleGroup fxErfidleikastig;

    @FXML
    private Button fxTilBaka;

    @FXML
    private RadioButton stig1;

    @FXML
    private RadioButton stig2;

    @FXML
    private RadioButton stig3;

    private DifficultyModel difficultyModel = DifficultyModel.getInstance();

    public void setDifficultyModel(DifficultyModel model) {
        this.difficultyModel = model;
        //initialize(); // Re-initialize to update based on model
    }

    @FXML
    private void onErfidleikastig() {
        DifficultyModel difficultyModel = DifficultyModel.getInstance();
        Toggle selectedToggle = fxErfidleikastig.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            difficultyModel.setDifficulty(selectedRadioButton.getText());
        }
    }

    @FXML
    public void initialize() {
        String currentDifficulty = DifficultyModel.getInstance().getDifficulty();
        switch (currentDifficulty) {
            case "Auðvelt":
                stig1.setSelected(true);
                break;
            case "Miðlungs":
                stig2.setSelected(true);
                break;
            case "Erfitt":
                stig3.setSelected(true);
                break;
            default:
                // Handle default case or throw an error
                break;
        }
    }

    @FXML
    private void onTilBaka(ActionEvent actionEvent) throws IOException {
        Stage nuverandiStage = (Stage) fxTilBaka.getScene().getWindow();
        nuverandiStage.close();
    }


}
