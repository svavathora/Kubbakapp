package hi.verkefni5.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Pair;
import vinnsla.Innskraning;

import java.io.IOException;

public class InnskraningDialog extends Dialog<Pair<String, String>> {

    //Tilviksbreytur
    //private Innskraning innskraning1;
   // private Innskraning innskraning2;
    @FXML
    private TextField fxNafnLeikmanns1;
    @FXML
    private TextField fxNafnLeikmanns2;

    @FXML
    private ButtonType fxHefjaLeik;

    public InnskraningDialog() {
        setDialogPane(lesaDialog());
        setResultConverter();
        iLagiRegla();

    }

    private void setResultConverter() {
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                String leikmadur1 = fxNafnLeikmanns1.getText();
                String leikmadur2 = fxNafnLeikmanns2.getText();
                return new Pair<>(leikmadur1, leikmadur2);
            } else {
                return null;
            }
        });
    }

    private DialogPane lesaDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("innskraning-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void iLagiRegla() {
        Node iLagi = getDialogPane().lookupButton(fxHefjaLeik);
        iLagi.disableProperty().bind(
                fxNafnLeikmanns1.textProperty().isEmpty()
                        .or(fxNafnLeikmanns2.textProperty().isEmpty()));
    }

    public static void main(String[] args) {

    }
}

