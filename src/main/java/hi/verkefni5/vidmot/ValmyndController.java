package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ValmyndController {

        //viðmótstilviksbreytur
        @FXML
        private GoldController goldController;

        @FXML
        private RadioMenuItem stig1;

        @FXML
        private RadioMenuItem stig2;

        @FXML
        private RadioMenuItem stig3;

        @FXML
        private MenuItem fxBreyta;

        //togglegroup
        private ToggleGroup erfidleikastig = new ToggleGroup();

        //erfiðleikastigin set í togglegroup
        public void initialize() {
            stig1.setToggleGroup(erfidleikastig);
            stig2.setToggleGroup(erfidleikastig);
            stig3.setToggleGroup(erfidleikastig);
        }

        /**
         * Þegar erfiðleikastig er valið
         */
        @FXML
        private void onErfidleikastig() {
            RadioMenuItem validItem = (RadioMenuItem) erfidleikastig.getSelectedToggle();
        }

        /**
         * Nýr leikur er hafinn
         */
        @FXML
        private void onNyrLeikur() {
            goldController.endurraesa();
        }

        /**
         * Getter fyrir erfiðleikastigin
         * @return erfiðleikastig
         */
        public RadioMenuItem getRadioMenuItem() {
            return (RadioMenuItem) erfidleikastig.getSelectedToggle();
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
                    "Svava Þóra smíðaði þetta forrit.");


            alert.showAndWait();
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


