package hi.verkefni5.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import vinnsla.Askrifandi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AskrifandiDialog extends Dialog<Askrifandi> {

    //Tilviksbreytur
    private Askrifandi askrifandi1;
    private Askrifandi askrifandi2;
    @FXML
    private TextField fxNafnLeikmanns1;
    @FXML
    private TextField fxNafnLeikmanns2;

    public AskrifandiDialog(Askrifandi askrifandi1, Askrifandi askrifandi2) {
        this.askrifandi1 = askrifandi1;
        this.askrifandi2 = askrifandi2;
        setDialogPane(lesaDialog());
        setResultConverter();
    }

    private void setResultConverter() {
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                askrifandi1.setNafn1(fxNafnLeikmanns1.getText());
                askrifandi2.setNafn2(fxNafnLeikmanns2.getText());
                List<Object> askrifendur = new ArrayList<>();
                askrifendur.add(askrifandi1);
                askrifendur.add(askrifandi2);
                return (Askrifandi) askrifendur;
            } else {
                return null;
            }
        });
    }

    private DialogPane lesaDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("askrifandi-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}

