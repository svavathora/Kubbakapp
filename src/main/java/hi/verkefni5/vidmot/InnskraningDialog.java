package hi.verkefni5.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import vinnsla.Innskraning;

import java.io.IOException;

public class InnskraningDialog extends Dialog<Innskraning> {

    //Tilviksbreytur
    private Innskraning innskraning1;
    private Innskraning innskraning2;
    @FXML
    private TextField fxNafnLeikmanns1;
    @FXML
    private TextField fxNafnLeikmanns2;

    public InnskraningDialog(Innskraning innskraning1, Innskraning innskraning2) {
        this.innskraning1 = innskraning1;
        this.innskraning2 = innskraning2;
        setDialogPane(lesaDialog());
        setResultConverter1();
        setResultConverter2();
    }

    private void setResultConverter1() {
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                innskraning1.setNafn1(fxNafnLeikmanns1.getText());
                return innskraning1;
            } else {
                return null;
            }
        });
    }

    private void setResultConverter2() {
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                innskraning2.setNafn2(fxNafnLeikmanns2.getText());
                return innskraning2;
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

    public static void main(String[] args) {

    }
}

